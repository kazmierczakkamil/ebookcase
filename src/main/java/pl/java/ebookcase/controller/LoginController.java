package pl.java.ebookcase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String logging() {
        return "login";
    }

    @PostMapping("/login/success")
    public String successLogin(ModelMap model, Principal principal) {
        String username = principal.getName();
        model.addAttribute("username", username);
        return "home";
    }

    @PostMapping("/login/failure")
    public String failureLogin() {
        return "login";
    }

}
