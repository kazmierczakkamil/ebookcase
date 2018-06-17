package pl.java.ebookcase.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.java.ebookcase.model.User;
import pl.java.ebookcase.service.UserService;

import javax.validation.Valid;

@AllArgsConstructor
@Controller
public class RegisterController {

    private UserService userService;

    @GetMapping("/register")
    public ModelAndView showForm() {
        return new ModelAndView("register", "user", new User());
    }

    @PostMapping("/register")
    public String submitReader(@Valid @ModelAttribute("user")User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors() || !processPasswords(user, bindingResult))
            return "error";

        User saved = userService.save(user);
        model.addAttribute("user", saved);
        return "redirect:/login";
    }


    private boolean processPasswords(User user, BindingResult bindingResult) {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            bindingResult.rejectValue("password", "Passwords don't match", "Passwords don't match");
            bindingResult.rejectValue("confirmPassword", "Passwords don't match", "Passwords don't match");
            return false;
        }
        return true;
    }

}
