package project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import project.dao.DishDao;
import project.dto.ProcessOrderDto;
import project.entities.Category;
import project.entities.Dish;
import project.entities.User;
import project.entities.common.Cart;
import project.entities.common.FilterMenu;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class DishService {

    private final DishDao dishDao;
    private final UserService userService;
    private final CategoryService categoryService;


    public Page<Dish> findAll(FilterMenuService filterMenuService, Pageable  pageable) {
        return dishDao.findAll(filterMenuService, pageable);
    }

    public Dish findById(Long dishId) {
        Optional<Dish> optional = dishDao.findById(dishId);
        return optional.orElseThrow(() -> new EntityNotFoundException(String.format("Dish with id %s is not found", dishId)));
    }

    public void saveOrUpdate(Dish dish) {
        dishDao.saveAndFlush(dish);
    }

    public void update(Dish dish) {
        dishDao.save(dish);
    }

    public void getDishes(Long category, Pageable pageable, FilterMenu filterMenu, Model model) {
        filterMenu.setCategoryId(category);
        FilterMenuService filterMenuService = new FilterMenuService(filterMenu);
        Page<Dish> pageDishes = findAll(filterMenuService, pageable);
        List<Dish> dishes = pageDishes.getContent();
        int totalPages = pageDishes.getTotalPages();
        List<Category> allCategories = categoryService.findAll();
        model.addAttribute("dishes", dishes);
        model.addAttribute("categories", allCategories);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("sort", pageable.getSort().toString());
        model.addAttribute("filterMenu", filterMenu);
    }

    public ProcessOrderDto showCart(HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        Cart cart = (Cart) session.getAttribute("cart");
        ProcessOrderDto processOrderDto;
        if ("anonymousUser".equals(login)) {
            processOrderDto = new ProcessOrderDto(cart, "", "", "");
        } else {
            User user = userService.findByLogin(login);
            processOrderDto = new ProcessOrderDto(cart,  String.format("%S %S", user.getFirstName(), user.getMiddleName()), user.getUserSettings().getPhone(), user.getUserSettings().getAddress());
        }
        return processOrderDto;
    }

    public void getStars(Long id, Model model) {
        Dish dish = findById(id);
        model.addAttribute("dish", dish);
        float a = dish.getStarOne() + dish.getStarTwo() * 2 + dish.getStarThree() * 3 + dish.getStarFour() * 4 + dish.getStarFive() * 5;
        float b = dish.getStarOne() + dish.getStarTwo() + dish.getStarThree() + dish.getStarFour() + dish.getStarFive();
        float delete = a / b;
        String total = String.format("%.1f", delete);
        model.addAttribute("total", total);
    }

    public void getDish(Long id, Model model) {
        Dish dish = findById(id);
        model.addAttribute("dish", dish);
        List<Category> allCategory = categoryService.findAll();
        model.addAttribute("allCategory", allCategory);
        model.addAttribute("idCategory", dish.getCategory().getId());
    }

    public void getRating(Long id, Integer stars) {
        Dish dish = findById(id);
        if (5 == stars) {
            dish.setStarFive(dish.getStarFive() + 1);
            update(dish);
        } else if (4 == stars) {
            dish.setStarFour(dish.getStarFour() + 1);
            saveOrUpdate(dish);
        } else if (3 == stars) {
            dish.setStarThree(dish.getStarThree() + 1);
            saveOrUpdate(dish);
        } else if (2 == stars) {
            dish.setStarTwo(dish.getStarTwo() + 1);
            saveOrUpdate(dish);
        } else {
            dish.setStarOne(dish.getStarOne() + 1);
            saveOrUpdate(dish);
        }
    }

    public void changeDish(Dish dish, Long categoryId, Boolean spicy, Boolean forVegans, Boolean withoutSugar, Boolean withoutGluten) {
        dish.setCategory(categoryService.findById(categoryId));
        dish.setSpicy(spicy);
        dish.setForVegans(forVegans);
        dish.setWithoutSugar(withoutSugar);
        dish.setWithoutGluten(withoutGluten);
        saveOrUpdate(dish);
    }
}
