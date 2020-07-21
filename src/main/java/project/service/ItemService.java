package project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dto.AllReservedDto;
import project.entities.Dish;
import project.entities.Item;
import project.entities.common.Cart;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final DishService dishService;
    private final ReserveTablesService reserveTablesService;


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
}
