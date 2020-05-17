package project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.service.OrderFoodService;
import project.service.UserService;


@Controller
@RequestMapping(value = "/main")
public class MainController {

    private final OrderFoodService orderFoodService;
    private final UserService userService;

    @Autowired
    public MainController(OrderFoodService orderFoodService, UserService userService) {
        this.orderFoodService = orderFoodService;
        this.userService = userService;
    }

    @GetMapping(value = "/user-settings")
    public String getUserSettings() {
        return "userSettings";
    }



}
