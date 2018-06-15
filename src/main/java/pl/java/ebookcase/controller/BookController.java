package pl.java.ebookcase.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.java.ebookcase.model.Book;
import pl.java.ebookcase.service.BookService;

import java.util.List;

@AllArgsConstructor
@Controller
public class BookController {

    private BookService bookService;

    @GetMapping("/books")
    public String showAllBooks(Model model) {
        List<Book> books = bookService.getBooks();
        model.addAttribute("books", books);
        return "books";
    }
}
