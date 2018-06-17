package pl.java.ebookcase.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.java.ebookcase.model.*;
import pl.java.ebookcase.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Controller
public class BookController {

    private BookService bookService;
    private ReviewService reviewService;
    private BookcaseRecordService bookcaseRecordService;

    @GetMapping("/books")
    public String showAllBooks(Model model) {
        List<Book> books = bookService.getBooks();
        model.addAttribute("books", books);
        model.addAttribute("bookcaseRecordService", bookcaseRecordService);
        return "booksList";
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
        User user = (User)session.getAttribute("user");
        bookcaseRecordService.save(book, user);
        String previousPage = request.getHeader("Referer");
        return "redirect:" + previousPage;
    }

    @GetMapping("/remove/book/{id}")
    public String removeBookFromBookcase(@PathVariable("id") Long id, HttpSession session, HttpServletRequest request) {
        Book book = bookService.getBookById(id);
        User user = (User)session.getAttribute("user");
        bookcaseRecordService.remove(book, user);
        String previousPage = request.getHeader("Referer");
        return "redirect:" + previousPage;
    }

    @GetMapping("/myBooks")
    public String showMyBooks(Model model, HttpSession session) {
        User user = (User)session.getAttribute("user");
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
        review.setBook((Book)session.getAttribute("book"));
        review.setUser((User)session.getAttribute("user"));
        return review;
    }



}
