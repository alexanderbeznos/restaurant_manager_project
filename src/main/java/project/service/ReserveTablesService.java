package project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import project.dao.OrderFoodDao;
import project.dao.ReserveTablesDao;
import project.dto.AllReservedDto;
import project.dto.OrderingDto;
import project.dto.AfterOrderingDto;
import project.dto.OrderingSuccessDto;
import project.entities.Item;
import project.entities.OrderFood;
import project.entities.ReserveTables;
import project.entities.User;
import project.entities.common.Cart;
import project.entities.common.OrderType;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ReserveTablesService {

    private final ReserveTablesDao reserveTablesDao;
    private final UserService userService;
    private final TablesService tablesService;
    private final OrderFoodDao orderFoodDao;

    public ReserveTables findById(Long reserveTableId) {
        Optional<ReserveTables> optional = reserveTablesDao.findById(reserveTableId);
        return optional.orElseThrow(() -> new EntityNotFoundException(String.format("Category with id %s is not found", reserveTableId)));
    }

    public String getSuccessReservePage(Long id, Model model) {
        ReserveTables reserveTables = findById(id);
        String startTime = changeLocalDateTimeToString(reserveTables.getStartTime());
        String finishTime = changeLocalDateTimeToString(reserveTables.getFinishTime());
        User user = userService.findById(reserveTables.getUser());
        String row = String.format("Уважаемый (ая) %s, ждём вас с %s до %s в количестве %d человек.", user.getFirstName(), startTime, finishTime, reserveTables.getNumberOfPeople());
        model.addAttribute("success", row);
        model.addAttribute("reservedId", id);
        return "successReservation";
    }

    public String changeLocalDateTimeToString(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
        return localDateTime.format(formatter);
    }

    public String getAllReserves(Model model, Principal principal) {
        List<AllReservedDto> reserves = getAllUserReserves(principal);
        model.addAttribute("reserves", reserves);
        return "allReservationUser";
    }

    public List<AllReservedDto> getAllUserReserves(Principal principal) {
        String login = principal.getName();
        User user = userService.findByLogin(login);
        return returnAllReserved(user);
    }

    public List<AllReservedDto> returnAllReserved(User user) {
        List<ReserveTables> allReserves = reserveTablesDao.findByUser(user.getId());
        LocalDateTime nowTime = LocalDateTime.now();
        List<ReserveTables> listReserves = allReserves.stream()
                .filter(f -> f.getStartTime().isAfter(nowTime))
                .collect(Collectors.toList());
        List<AllReservedDto> reserves = listReserves.stream().
                map(m -> {
                    String startTime = changeLocalDateTimeToString(m.getStartTime());
                    String finishTime = changeLocalDateTimeToString(m.getStartTime());
                    AllReservedDto reservedDto = new AllReservedDto();
                    reservedDto.setReservedId(m.getId());
                    reservedDto.setStartDate(startTime);
                    reservedDto.setFinishDate(finishTime);
                    reservedDto.setTableNumber(m.getTableNumber());
                    reservedDto.setNumberOfPeople(m.getNumberOfPeople());
                    return reservedDto;
                })
                .collect(Collectors.toList());
        return reserves;
    }

    public String deleteReserve(Long id, Model model, Principal principal) {
        String login = principal.getName();
        User user = userService.findByLogin(login);
        ReserveTables reserveTables = findById(id);
        reserveTablesDao.delete(reserveTables);
        List<AllReservedDto> reserves = returnAllReserved(user);
        model.addAttribute("reserves", reserves);
        return "allReservationUser";
    }

    public String getPreReserve(Model model, Principal principal) {
        List<AllReservedDto> allUserReserves = getAllUserReserves(principal);
        model.addAttribute("reserves", allUserReserves);
        return "preOrder";
    }

    public String ordering(Long id, Model model, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        List<Item> products = cart.getProducts();
        ReserveTables reserveTables = findById(id);
        String startTime = changeLocalDateTimeToString(reserveTables.getStartTime());
        String finishTime = changeLocalDateTimeToString(reserveTables.getFinishTime());
        String head = String.format("Предзаказ к бронированию с %s до %s(столик №%d)", startTime, finishTime, reserveTables.getTableNumber());
        List<OrderingDto> productsDto = new ArrayList<>();
        for (int i = 1; i <= products.size(); i++) {
            productsDto.add(new OrderingDto(i, products.get(i - 1).getDish(), (products.get(i - 1).getCount())));
        }
        model.addAttribute("products", productsDto);
        model.addAttribute("reserveTablesId", id);
        model.addAttribute("head", head);
        return "ordering";
    }

    public AfterOrderingDto orderingSuccess(List<OrderingSuccessDto> orderingSuccessDto, HttpSession session, Principal principal) {
        Cart cart = (Cart) session.getAttribute("cart");
        List<Item> products = cart.getProducts();

        boolean success = true;
        ReserveTables reserveTablesId = findById(orderingSuccessDto.get(0).getReserveTablesId());
        for (OrderingSuccessDto successDto : orderingSuccessDto) {
            if (!successDto.getTimeDish().isEmpty()) {
                LocalDateTime time = tablesService.changeStringToLocalDateTime(successDto.getTimeDish());
                if (!(time.isAfter(reserveTablesId.getStartTime().minusMinutes(1)) && time.isBefore(reserveTablesId.getFinishTime()))) {
                    success = false;
                    break;
                }
            }
        }
        if (!success) {
            return new AfterOrderingDto(false);
        } else {
            OrderFood orderFood = new OrderFood();
            orderFood.setOrderType(OrderType.R);
            orderFood.setReserveTable(findById(orderingSuccessDto.get(0).getReserveTablesId()));
            orderingSuccessDto.forEach(o -> {
                products.forEach(p -> {
                    if (o.getDishId().equals(p.getDish().getId())) {
                        if (!o.getComment().isEmpty()) {
                            p.setComment(o.getComment());
                        }
                        if (!o.getTimeDish().isEmpty()) {
                            p.setServingTime(tablesService.changeStringToLocalDateTime(o.getTimeDish()));
                        }
                        p.setOrder(orderFood);
                    }
                });
            });
            orderFood.setItems(products);
            String login = principal.getName();
            User user = userService.findByLogin(login);
            orderFood.setUser(user);
            orderFoodDao.saveAndFlush(orderFood);
            cart.setProducts(new ArrayList<>());
            return new AfterOrderingDto(true);
        }
    }
}
