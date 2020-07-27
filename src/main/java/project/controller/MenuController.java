package project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import project.dto.ProcessOrderDto;
import project.entities.Dish;
import project.entities.User;
import project.entities.common.Cart;
import project.entities.common.FilterMenu;
import project.service.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;


@Controller
@RequestMapping(value = "/menu")
@RequiredArgsConstructor
public class MenuController {

    private final DishService dishService;
    private final OrderFoodService orderFoodService;
    private final ItemService itemService;

    @GetMapping(value = "/category/{category}")
    public String getDishes(@PathVariable(value = "category") Long category,
                            @PageableDefault(page = 0, size = 6)
                            @SortDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                            FilterMenu filterMenu,
                            Model model) {
        dishService.getDishes(category, pageable, filterMenu, model);
        return "menu";
    }

    @GetMapping(value = "/cart")
    public String getCart(ModelMap modelMap) {
        Cart cart = new Cart();
        modelMap.put("cart", cart.findAll());
        return "product/index";
    }

    @ResponseBody
    @GetMapping(value = "/put/{id}")
    public Cart put(@PathVariable("id") Long id, HttpSession session, Principal principal) {
        return itemService.put(id, session, principal);
    }

    @ResponseBody
    @GetMapping(value = "/remove/{id}")
    public Cart remove(@PathVariable("id") Long id, HttpSession session, Principal principal) {
        return itemService.remove(id, session, principal);
    }

    @ResponseBody
    @GetMapping(value = "/show-cart-order")
    public ProcessOrderDto showCartOrder(HttpSession session) {
        return dishService.showCart(session);
    }

    @ResponseBody
    @GetMapping(value = "/show-cart")
    public Cart showCart(HttpSession session) {
        return (Cart) session.getAttribute("cart");
    }

    @GetMapping(value = "/about-dish/{id}")
    public String getStars(@PathVariable("id") Long id, Model model) {
        dishService.getStars(id, model);
        return "aboutDish";
    }

    @ResponseBody
    @PostMapping(value = "/rating-dish/{id}/{stars}")
    public void getRatingDish(@PathVariable("id") Long id, @PathVariable("stars") Integer stars) {
        dishService.getRating(id, stars);
    }

    @GetMapping(value = "/process-order")
    public String processOrder() {
        return "processOrder";
    }

    @PostMapping(value = "/success-order")
    public String successOrder(@RequestParam Map<String, String> map, HttpSession session, Principal principal) {
        orderFoodService.save(map, session, principal);
        return "successOrder";
    }

    @GetMapping(value = "/dishes/{category}")
    public String getDishesForAdmin(@PathVariable(value = "category") Long category,
                            @PageableDefault(page = 0, size = 6)
                            @SortDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                            FilterMenu filterMenu,
                            Model model) {
        dishService.getDishes(category, pageable, filterMenu, model);
        return "dishesForChange";
    }

    @GetMapping(value = "/about-dish/change/{id}")
    public String getDishChange(@PathVariable("id") Long id, Model model) {
        dishService.getDish(id, model);
        return "dishForChange";
    }

    @PostMapping(value = "/changing")
    public String getChanging(@ModelAttribute Dish dish,
                              @RequestParam(name = "categoryId") Long categoryId,
                              @RequestParam(name = "spicy") Boolean spicy,
                              @RequestParam(name = "forVegans") Boolean forVegans,
                              @RequestParam(name = "withoutSugar") Boolean withoutSugar,
                              @RequestParam(name = "withoutGluten") Boolean withoutGluten,
                              Model model) {
        dishService.changeDish(dish, categoryId, spicy, forVegans, withoutSugar, withoutGluten);
        dishService.getDish(dish.getId(), model);
        return "dishForChange";
    }

}
