package pl.java.ebookcase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @GetMapping({"/", "/main", "/index"})
    public String index(HttpServletRequest request) {
        if (request.isUserInRole("USER") || request.isUserInRole("ADMIN"))
            return "home";
        return "index";
    }
}
