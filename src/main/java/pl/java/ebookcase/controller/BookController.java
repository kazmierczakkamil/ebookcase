package pl.java.ebookcase.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.java.ebookcase.model.*;
import pl.java.ebookcase.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@AllArgsConstructor
@Controller
public class BookController {

    private BookService bookService;
    private ReviewService reviewService;
    private BookcaseRecordService bookcaseRecordService;
    private AuthorService authorService;
    private CategoryService categoryService;

    @GetMapping("/books")
    public String showAllBooks(Model model) {
        List<Book> books = bookService.getBooks();
        model.addAttribute("books", books);
        model.addAttribute("bookcaseRecordService", bookcaseRecordService);
        return "booksList";
    }

    @GetMapping("/authors")
    public String showAllAuthors(Model model) {
        List<Author> authors = authorService.getAuthors();
        model.addAttribute("authors", authors);
        model.addAttribute("bookService", bookService);
        return "authorsList";
    }

    @GetMapping("/categories")
    public String showAllCategories(Model model) {
        List<Category> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("bookService", bookService);
        return "categoriesList";
    }

    @GetMapping("book/{id}")
    public String showBook(@PathVariable("id") Long id, Model model, HttpSession session) {
        Book book = bookService.getBookById(id);
        List<Review> reviews = reviewService.getReviewsByBookId(id);
        session.setAttribute("book", book);
        model.addAttribute("reviews", reviews);
        return "book";
    }

    @GetMapping("/add/book/{id}")
    public String addBookToBookcase(@PathVariable("id") Long id, HttpSession session, HttpServletRequest request) {
        Book book = bookService.getBookById(id);
        User user = (User) session.getAttribute("user");
        bookcaseRecordService.save(book, user);
        String previousPage = request.getHeader("Referer");
        return "redirect:" + previousPage;
    }

    @GetMapping("/remove/book/{id}")
    public String removeBookFromBookcase(@PathVariable("id") Long id, HttpSession session, HttpServletRequest request) {
        Book book = bookService.getBookById(id);
        User user = (User) session.getAttribute("user");
        bookcaseRecordService.remove(book, user);
        String previousPage = request.getHeader("Referer");
        return "redirect:" + previousPage;
    }

    @GetMapping("/myBooks")
    public String showMyBooks(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<BookcaseRecord> records = bookcaseRecordService.getBookcaseRecordsByUser(user);
        model.addAttribute("records", records);
        model.addAttribute("bookcaseRecordService", bookcaseRecordService);
        return "myBooks";
    }

    @PostMapping("/addReview")
    public String addReview(@ModelAttribute("newReview") Review review) {
        Review saved = reviewService.save(review);
        return "redirect:/book/" + saved.getBook().getId();
    }

    @ModelAttribute("newReview")
    public Review newReview(HttpSession session) {
        Review review = new Review();
        review.setBook((Book) session.getAttribute("book"));
        review.setUser((User) session.getAttribute("user"));
        return review;
    }

    @GetMapping("/newBook")
    public String newBook() {
        return "newBook";
    }


}
