package project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import project.dao.ItemDao;
import project.dto.AllReservedDto;
import project.dto.ItemsForKitchenDto;
import project.entities.Dish;
import project.entities.Item;
import project.entities.common.Cart;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final DishService dishService;
    private final ReserveTablesService reserveTablesService;
    private final ItemDao itemDao;

    public Cart put(Long id, HttpSession session, Principal principal) {
        Cart cart;
        List<AllReservedDto> allUserReserves = new ArrayList<>();
        if (principal != null) {
            allUserReserves = reserveTablesService.getAllUserReserves(principal);
        }
        if (session.getAttribute("cart") == null) {
            cart = new Cart();
            Dish dish = dishService.findById(id);
            cart.add(new Item(dish, 1));
            session.setAttribute("cart", cart);
            if (allUserReserves.size() > 0) {
                cart.setReserved(true);
            } else {
                cart.setReserved(false);
            }
            return cart;
        } else {
            cart = (Cart) session.getAttribute("cart");
            boolean existInCart = cart.exists(id);
            if (!existInCart) {
                cart.add(new Item(dishService.findById(id), 1));
            } else {
                int quantity = cart.find(id).getCount() + 1;
                cart.find(id).setCount(quantity);
            }
            if (allUserReserves.size() > 0) {
                cart.setReserved(true);
            } else {
                cart.setReserved(false);
            }
            return cart;
        }
    }

    public Cart remove(Long id, HttpSession session, Principal principal) {
        Cart cart = (Cart) session.getAttribute("cart");
        List<AllReservedDto> allUserReserves = new ArrayList<>();
        if (principal != null) {
            allUserReserves = reserveTablesService.getAllUserReserves(principal);
        }
        if (allUserReserves.size() > 0) {
            cart.setReserved(true);
        } else {
            cart.setReserved(false);
        }
        Item item = cart.find(id);
        if (item == null) {
            return cart;
        }
        int quantity = item.getCount();
        if (quantity == 1) {
            cart.remove(item);
            return cart;
        } else {
            quantity--;
            cart.find(id).setCount(quantity);
            return cart;
        }
    }

    public Page<Item> findAll(Pageable pageable) {
        return itemDao.findAllItemWithPagination(pageable);
    }

    public String getOrderingDishes(Pageable pageable, Model model) {
        Page<Item> pageItems = findAll(pageable);
        List<Item> listItems = pageItems.getContent();
        List<ItemsForKitchenDto> items = new ArrayList<>();
        int totalPages = pageItems.getTotalPages();
        LocalDateTime now = LocalDateTime.now();
        boolean delivery = true;
        for (int i = 1; i <= listItems.size(); i++) {
            String servingTime = "";
            String difference = "";
            String comment ="";
            if ("RESTAURANT".equals(listItems.get(i - 1).getOrder().getOrderType().getOrder())) {
                delivery = false;
            }
            if (listItems.get(i - 1).getServingTime() != null) {
                servingTime = reserveTablesService.changeLocalDateTimeToString(listItems.get(i - 1).getServingTime());
                LocalDateTime time = listItems.get(i - 1).getServingTime();
//                long years = now.until(time, ChronoUnit.YEARS);
//                long months = now.until(time, ChronoUnit.MONTHS);
//                long days = now.until(time, ChronoUnit.DAYS);
                long hours = now.until(time, ChronoUnit.HOURS);
                long minutes = now.until(time, ChronoUnit.MINUTES);
                difference = String.format("%d ч : %d мин", hours, minutes - hours * 60);
            }
            comment = listItems.get(i - 1).getComment();
            items.add(new ItemsForKitchenDto(i, listItems.get(i - 1).getDish(), listItems.get(i - 1).getCount(), servingTime, difference, comment, delivery));
        }
        model.addAttribute("items", items);
        model.addAttribute("totalPages", totalPages);
        return "kitchen";
    }

    public String takeDish(Integer row, Pageable pageable) {
        Page<Item> pageItems = findAll(pageable);
        List<Item> items = pageItems.getContent();
        items.get(row - 1).setTake(true);
        return "";
    }
}
