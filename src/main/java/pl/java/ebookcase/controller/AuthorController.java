package pl.java.ebookcase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.java.ebookcase.model.Author;
import pl.java.ebookcase.model.Category;
import pl.java.ebookcase.service.AuthorService;

import java.util.List;

@Controller
public class AuthorController {

    private AuthorService authorService;

    @GetMapping("/newauthor")
    public String newAuthor() {
        return "newAuthor";
    }

    @GetMapping("/authors")
    public String showAllBooks(Model model) {
        List<Author> authors = authorService.getAuthors();
        model.addAttribute("authors", authors);
        return "authorsList";
    }
}
