package pl.java.ebookcase.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.java.ebookcase.model.User;
import pl.java.ebookcase.service.UserService;

import javax.servlet.http.HttpSession;

@AllArgsConstructor
@Controller
public class LoginController {

    private UserService userService;

    @GetMapping("/login")
    public String logging() {
        return "login";
    }

    @PostMapping("/login/success")
    public String successLogin(HttpSession session) {
        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByLogin(principal.getUsername());
        String nameAndSurname = user.getName() + " " + user.getSurname();

        session.setAttribute("user", user);
        session.setAttribute("username", nameAndSurname);

        return "redirect:/";
    }

    @PostMapping("/login/failure")
    public String failureLogin() {
        return "failure";
    }

}
