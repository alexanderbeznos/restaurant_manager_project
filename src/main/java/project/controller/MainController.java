package project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.entities.OrderFood;
import project.service.OrderFoodService;
import project.service.UserService;

import java.util.List;

@Controller
@RequestMapping(value = "/")
public class MainController {

    private final OrderFoodService orderFoodService;
    private final UserService userService;

    @Autowired
    public MainController(OrderFoodService orderFoodService, UserService userService) {
        this.orderFoodService = orderFoodService;
        this.userService = userService;
    }

    @GetMapping(value = "")
    public String home() {
        return "redirect: list";
    }

    @GetMapping(value = "list")
    public String getPlayers(Model model) {
        List<OrderFood> list = orderFoodService.findAll();
        model.addAttribute("orderFood", list);

        return "orderFood";
    }
    @GetMapping(value = "match")
    public String getP(Model model) {


        return "login";
    }
}
