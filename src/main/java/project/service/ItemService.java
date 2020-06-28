package project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.entities.Dish;
import project.entities.Item;
import project.entities.common.Cart;

import javax.servlet.http.HttpSession;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final DishService dishService;


    public Cart put(Long id, HttpSession session) {
        Cart cart;
        if (session.getAttribute("cart") == null) {
            cart = new Cart();
            Dish dish = dishService.findById(id);
            cart.add(new Item(dish, 1));
            session.setAttribute("cart", cart);
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
            return cart;
        }
    }

    public Cart remove(Long id, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
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
