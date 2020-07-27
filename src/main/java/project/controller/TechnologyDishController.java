package project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/technology")
@RequiredArgsConstructor
public class TechnologyDishController {

    @GetMapping(value = "/all")
    public String getTechnologyForAllDishes() {
        return "technology";
    }
}
