package project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import project.entities.User;
import project.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping(value = "/main")
public class MainController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MainController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = "/user-settings")
    public String getUserSettings(Model model, Principal principal) {
        String login = principal.getName();
        User user = userService.findByLogin(login);
        model.addAttribute("user", user);
        return "userSettings";
    }

    @GetMapping(value = "/settings-change")
    public String getSettingsChange(Model model, Principal principal) {
        String login = principal.getName();
        User user = userService.findByLogin(login);
        model.addAttribute("user", user);
        return "userSettingsChange";
    }

    @PostMapping(value = "/settings-change-post")
    public String getSettingsChangePost(@ModelAttribute @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            allErrors.forEach(a -> {
                model.addAttribute(((FieldError) a).getField().replace(".", "") + "Error", a.getDefaultMessage());
            });
            return "userSettingsChange";
        }
        userService.saveOrUpdate(user);
        return "userSettings";

    }

    @GetMapping(value = "/password-change")
    public String getPasswordChange(Model model, Principal principal) {
        String login = principal.getName();
        User user = userService.findByLogin(login);
        model.addAttribute("user", user);
        return "userPasswordChange";
    }

    @PostMapping(value = "/password-change-post")
    public String getPasswordChangePost(String oldPassword, String newPassword1, String newPassword2, Principal principal, Model model) {
        boolean errors = false;
        String currentPassword = userService.findByLogin(principal.getName()).getPassword();
        if (!newPassword1.equals(newPassword2)) {
            model.addAttribute("differentNewPassword", "Введённые новые пароли не совпадают");
            errors = true;
        }
        if (!passwordEncoder.matches(oldPassword, currentPassword)) {
            model.addAttribute("wrongPassword", "Введён неправильный пароль");
            errors = true;
        }
        if (passwordEncoder.matches(newPassword1, currentPassword)) {
            model.addAttribute("notDifferent1", "Введённый пароль идентиичен с текущим");
            errors = true;
        }
        if (passwordEncoder.matches(newPassword2, currentPassword)) {
            model.addAttribute("notDifferent2", "Введённый пароль идентиичен с текущим");
            errors = true;
        }
        if (!(newPassword1.length() > 6)) {
            model.addAttribute("shortPassword", "Минимум 7 символов");
            errors = true;
        }
        if (!errors) {
            User thisUser = userService.findByLogin(principal.getName());
            thisUser.setPassword(newPassword1);
            userService.saveAndFlush(thisUser);
            return "redirect: user-settings";
        }
        return "userPasswordChange";
    }
}
