package pl.java.ebookcase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping({"/", "/main", "/index"})
    public String index() {
        return "index";
    }

}