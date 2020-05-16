package project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.service.OrderFoodService;
import project.service.UserService;


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
        return "redirect: main";
    }

    @GetMapping(value = "main")
    public String getMain(Model model) {
        return "main";
    }
}
