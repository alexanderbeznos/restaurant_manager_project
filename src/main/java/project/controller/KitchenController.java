package project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import project.service.ItemService;

@Controller
@RequestMapping(value = "/kitchen")
@RequiredArgsConstructor
public class KitchenController {

    private final ItemService itemService;

    @GetMapping(value = "/all-orders")
    public String getOrderingDishes(@PageableDefault(page = 0, size = 9) Pageable pageable,
                                    Model model) {
        return itemService.getOrderingDishes(pageable, model);
    }

    @ResponseBody()
    @GetMapping(value = "/take-dish/{row}")
    public String takeDish(@PathVariable("row") Integer row,
                           @PageableDefault(page = 0, size = 9) Pageable pageable) {
        return itemService.takeDish(row, pageable);
    }

}
