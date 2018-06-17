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
        Category cat4 = new Category("Popularnonaukowe");
        Category cat5 = new Category("Historyczne");
        Category cat6 = new Category("Fantasy");
        Category cat7 = new Category("Romans");

        Author author1 = new Author("Paweł", "Ograbek");
        Author author2 = new Author("Kamil", "Kaźmierczak");
        Author author3 = new Author("Antoni", "Macierewicz");
        Author author4 = new Author("Krzysztof", "Krawczyk");

        Book book1 = new Book("Koszmar z ulicy wiązów", author1, 210, cat1);
        Book book2 = new Book("Jak zaliczyć PKCK", author1, 376, cat2);
        Book book3 = new Book("Coś śmiesznego", author2, 590, cat3);
        Book book4 = new Book("Przemierzyłem cały świat", author4, 405, cat5);
        Book book5 = new Book("Od Las Vegas po Krym", author4, 243, cat5);
        Book book6 = new Book("Zgrałem tysiąc talii kart", author4, 196, cat5);
        Book book7 = new Book("Które lubią dym", author4, 356, cat5);
        Book book8 = new Book("Misiewicz - prawdziwa historia", author3, 400, cat7);
        Book book9 = new Book("Jak zostać inżynierem", author1, 412, cat6);
        Book book10 = new Book("Jak to jest zrobione?", author2, 128, cat4);

        User user1 = new User("some", "Rafał", "Wójcik", "piespies", "piespies", "pies@com.pl");
        User user2 = new User("other", "Antek", "Bąbel", "piespies", "piespies", "pies1@com.pl");
        User user3 = new User("aaa", "Andrzej", "Podleśny", "aaaaa", "aaaaa", "aa@aa.aa");

        categoryRepository.save(cat1);
        categoryRepository.save(cat2);
        categoryRepository.save(cat3);
        categoryRepository.save(cat4);
        categoryRepository.save(cat5);
        categoryRepository.save(cat6);
        categoryRepository.save(cat7);
        authorRepository.save(author1);
        authorRepository.save(author2);
        authorRepository.save(author3);
        authorRepository.save(author4);
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        bookRepository.save(book4);
        bookRepository.save(book5);
        bookRepository.save(book6);
        bookRepository.save(book7);
        bookRepository.save(book8);
        bookRepository.save(book9);
        bookRepository.save(book10);
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
