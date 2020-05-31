package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.entities.Category;
import project.entities.Dish;
import project.service.CategoryService;
import project.service.DishService;

import java.util.List;

@Controller
@RequestMapping(value = "/menu")
public class MenuController {

    private final DishService dishService;
    private final CategoryService categoryService;

    @Autowired
    public MenuController(DishService dishService, CategoryService categoryService) {
        this.dishService = dishService;
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/category/{category}")
    public String getDishes(@PathVariable(value = "category") Long category,
                            @RequestParam(value = "page", required = false, defaultValue = "0") int page, Model model) {
        Category kind = categoryService.findById(category);
        Page<Dish> pageDishes = dishService.findByCategory(kind, PageRequest.of(page, 6));
        List<Dish> dishes = pageDishes.getContent();
        int totalPages = pageDishes.getTotalPages();
        List<Category> allCategories = categoryService.findAll();
        model.addAttribute("dishes", dishes);
        model.addAttribute("categories", allCategories);
        model.addAttribute("totalPages", totalPages);
        return "menu";
    }







}
