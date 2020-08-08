package project;

import project.entities.Dish;
import project.entities.Item;
import project.entities.common.Cart;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    public static Dish createDish() {
        Dish dish = new Dish();
        dish.setId(1L);
        return dish;
    }

    public static Cart createCart() {
        Cart cart = new Cart();
        Item item = new Item();
        item.setDish(createDish());
        item.setCount(1);
        List<Item> listItems = new ArrayList<>();
        listItems.add(item);
        cart.setProducts(listItems);
        return cart;
    }
}
