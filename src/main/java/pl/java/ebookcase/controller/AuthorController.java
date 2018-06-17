package pl.java.ebookcase.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.java.ebookcase.model.Author;
import pl.java.ebookcase.service.AuthorService;

import java.util.List;

@AllArgsConstructor
@Controller
public class AuthorController {

    private AuthorService authorService;

    @GetMapping("/authors")
    public String authors(Model model) {
        List<Author> authors = authorService.getAuthors();
        model.addAttribute("authors", authors);
        return "authorsList";
    }

    @GetMapping("/addAuthor")
    public String newAuthor(Model model) {
        return "newAuthor";
    }
}
