package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import project.dao.RolesDao;
import project.entities.Dish;
import project.entities.Roles;
import project.entities.UserSettings;
import project.entities.common.Cart;
import project.security.UserValidator;
import project.entities.User;
import project.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/")
public class AuthenticController {

    private final UserService userService;
    private final UserValidator userValidator;
    private final RolesDao rolesDao;

    @Autowired
    public AuthenticController(UserService userService, UserValidator userValidator, RolesDao rolesDao) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.rolesDao = rolesDao;
    }

    @GetMapping(value = "registration")
    public String getRegistration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping(value = "registration")
    public String getRegistration(@ModelAttribute @Valid User user, BindingResult result, Model model) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            allErrors.forEach(a -> {
                model.addAttribute(((FieldError) a).getField(), a.getDefaultMessage());
            });
            return "registration";
        }
        UserSettings userSettings = new UserSettings();
        user.setUserSettings(userSettings);
        Roles role = rolesDao.findByName("user");
        Set<Roles> setRoles = new HashSet<>();
        setRoles.add(role);
        user.setRoles(setRoles);
        userService.saveAndFlush(user);
        return "redirect: login";
    }

    @GetMapping(value = "login")
    public String login(@RequestParam(name = "error", required = false) Boolean mistake, Model model) {
        if (Boolean.TRUE.equals(mistake)) {
            model.addAttribute("errorKey", true);
        }
        return "login";
    }

//    @GetMapping(value = "")
//    public String getMenu() {
//        return "redirect: menu/category/1";
//    }

    @GetMapping(value = "")
    public String getMenu(HttpSession session) {
        //TODO Move to sessionListener
        if (session.getAttribute("cart") == null) {
            Cart cart = new Cart();
            session.setAttribute("cart", cart);
        }
        return "redirect:menu/category/1";
    }


}
