package pl.java.ebookcase.bootstrap;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.java.ebookcase.model.*;
import pl.java.ebookcase.repository.*;
import pl.java.ebookcase.service.UserService;

@AllArgsConstructor
@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private BookcaseRepository bookcaseRepository;
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private UserService userService;
    private CategoryRepository categoryRepository;
    private BookcaseRecordRepository bookcaseRecordRepository;
    private ReviewRepository reviewRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        init();
    }

    private void init() {
        Category cat1 = new Category("Horror");
        Category cat2 = new Category("Komedia");
        Category cat3 = new Category("Sensacja");
        Author author1 = new Author("Pawel", "Ograbek");
        Author author2 = new Author("Kamil", "Kaźmierczak");
        Book book1 = new Book("Tytul", author1, 200, cat1);
        Book book2 = new Book("Jak zaliczyć PKCK", author1, 300, cat2);
        Book book3 = new Book("Coś", author2, 400, cat3);
        User user1 = new User("some", "Rafal", "Wojcik", "piespies", "piespies", "pies@com.pl");
        User user2 = new User("other", "Antek", "Babel", "piespies", "piespies", "pies1@com.pl");
        User user3 = new User("aaa", "aaa", "aaa", "aaaaa", "aaaaa", "aa@aa.aa");

        categoryRepository.save(cat1);
        categoryRepository.save(cat2);
        categoryRepository.save(cat3);
        authorRepository.save(author1);
        authorRepository.save(author2);
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        user1 = userService.save(user1);
        user2 = userService.save(user2);
        user3 = userService.save(user3);

        Review review1 = new Review("Bardzo dobra książka.", book1, user1);
        Review review2 = new Review("Wy****** mam w to.", book1, user2);



        BookcaseRecord record1 = new BookcaseRecord(book1, user1.getBookcase());
        BookcaseRecord record2 = new BookcaseRecord(book2, user1.getBookcase());

        bookcaseRecordRepository.save(record1);
        bookcaseRecordRepository.save(record2);
        reviewRepository.save(review1);
        reviewRepository.save(review2);
    }
}
