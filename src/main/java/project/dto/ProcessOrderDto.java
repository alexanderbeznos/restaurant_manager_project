package project.dto;

import lombok.Getter;
import lombok.Setter;
import project.entities.common.Cart;

@Setter
@Getter
public class ProcessOrderDto {

    private Cart cart;
    private String name;
    private String phone;
    private String address;

    public ProcessOrderDto(Cart cart, String name, String phone, String address) {
        this.cart = cart;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
}
