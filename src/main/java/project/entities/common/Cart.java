package project.entities.common;

import lombok.Getter;
import lombok.Setter;
import project.entities.Item;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Cart {

    private List<Item> products = new ArrayList<>();
    private boolean reserved;

    public List<Item> findAll() {
        return this.products;
    }

    public Item find(Long id) {
        for (Item product : this.products) {
            if (product.getDish().getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public void add(Item item) {
        products.add(item);
    }

    public boolean exists(Long id) {
        for (int i = 0; i < this.products.size(); i++) {
            if (this.findAll().get(i).getDish().getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void remove(Item item) {
        products.remove(item);
    }


}
