package project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dao.OrderFoodDao;
import project.entities.Item;
import project.entities.OrderFood;
import project.entities.User;
import project.entities.common.Cart;
import project.entities.common.OrderType;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderFoodService {

    private final OrderFoodDao orderFoodDao;
    private final UserService userService;

    public List<OrderFood> findAll() {
        return orderFoodDao.findAll();
    }

    public void save(Map<String, String> map, HttpSession session, Principal principal) {
        Cart cart = (Cart) session.getAttribute("cart");
        OrderFood orderFood = new OrderFood();
        orderFood.setOrderType(OrderType.D);
        if (principal != null) {
            String login = principal.getName();
            User user = userService.findByLogin(login);
            orderFood.setUser(user);

        }
        orderFood.setNameOfUser(map.remove("name"));
        orderFood.setPhone(map.remove("phone"));
        orderFood.setAddress(map.remove("address"));
        orderFood.setDescription(map.remove("comment"));

        for (int i = 0; i < map.size(); i++) {
            Item item = cart.getProducts().get(i);
            item.setComment(map.get(String.valueOf(i)));
            item.setOrder(orderFood);
        }
        orderFood.setItems(cart.getProducts());
        orderFoodDao.saveAndFlush(orderFood);
        cart.setProducts(new ArrayList<>());
    }
}
