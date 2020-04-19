package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.dao.RolesDao;
import project.entities.Roles;
import project.entities.UserSettings;
import project.security.UserValidator;
import project.entities.User;
import project.service.UserService;

import javax.validation.Valid;
import java.util.HashSet;
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
        return "/authentic/registration";
    }

    @PostMapping(value = "registration")
    public String getRegistration(@ModelAttribute @Valid User user, BindingResult result, Model model) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            model.addAttribute("result", result);
            return "/authentic/registration";
        }
        UserSettings userSettings = new UserSettings();
        user.setUserSettings(userSettings);
        Roles role = rolesDao.findByName("user");
        Set<Roles> setRoles = new HashSet<>();
        setRoles.add(role);
        user.setRoles(setRoles);
        userService.saveAndFlush(user);
        return "redirect: list";
    }

    @GetMapping(value = "login")
    public String login(@RequestParam(name = "error", required = false) Boolean mistake, Model model) {
        if (Boolean.TRUE.equals(mistake)) {
            model.addAttribute("errorKey", true);
        }
        return "authentic/login";
    }
}
