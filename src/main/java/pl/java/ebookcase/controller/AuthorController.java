package pl.java.ebookcase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorController {

    @GetMapping("/newauthor")
    public String newAuthor() {
        return "newAuthor";
    }
}
