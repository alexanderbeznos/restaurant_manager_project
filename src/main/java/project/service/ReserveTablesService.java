package project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import project.dao.CategoryDao;
import project.dao.ReserveTablesDao;
import project.dto.AllReservedDto;
import project.dto.TableDto;
import project.entities.Category;
import project.entities.ReserveTables;
import project.entities.User;

import javax.persistence.EntityNotFoundException;
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

    public ReserveTables findById(Long reserveTableId) {
        Optional<ReserveTables> optional = reserveTablesDao.findById(reserveTableId);
        return optional.orElseThrow(() -> new EntityNotFoundException(String.format("Category with id %s is not found", reserveTableId)));
    }

    public String getSuccessReservePage(Long id, Model model) {
        ReserveTables reserveTables = findById(id);
        String startTime = changeLocalDateTimeToString(reserveTables.getStartTime());
        String finishTime = changeLocalDateTimeToString(reserveTables.getFinishTime());
        User user = userService.findById(reserveTables.getUser());
        String row = String.format("Уважаемый %s, ждём вас с %s до %s в количестве %d человек", user.getFirstName(), startTime, finishTime, reserveTables.getNumberOfPeople());
        model.addAttribute("success", row);
        model.addAttribute("reservedId", id);
        return "successReservation";
    }

    public String changeLocalDateTimeToString(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
        return localDateTime.format(formatter);
    }

    public String getAllReserves(Model model, Principal principal) {
        String login = principal.getName();
        User user = userService.findByLogin(login);
        List<AllReservedDto> reserves = returnAllReserved(user);
        model.addAttribute("reserves", reserves);
        return "allReservationUser";
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
        ReserveTables reserveTables = new ReserveTables();
        reserveTables.setId(id);
        reserveTablesDao.delete(reserveTables);
        List<AllReservedDto> reserves = returnAllReserved(user);
        model.addAttribute("reserves", reserves);
        return "allReservationUser";
    }



}
