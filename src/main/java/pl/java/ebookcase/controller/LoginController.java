package pl.java.ebookcase.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.java.ebookcase.model.User;
import pl.java.ebookcase.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@AllArgsConstructor
@Controller
public class LoginController {

    private UserService userService;

    @GetMapping("/login")
    public String logging() {
        return "login";
    }

    @PostMapping("/login/success")
    public String successLogin(Model model) {
        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();
        User user = userService.getUserByLogin(username);
        model.addAttribute("user", user);
        model.addAttribute("username", username);
        return "redirect:/home";
    }

    @PostMapping("/login/failure")
    public String failureLogin() {
        return "failure";
    }

}
