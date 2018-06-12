package pl.java.ebookcase.bootstrap;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.java.ebookcase.model.*;
import pl.java.ebookcase.repositories.*;

import java.util.HashSet;
import java.util.Set;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private BookcaseRepository bookcaseRepository;
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private UserRepository userRepository;
    private CategoryRepository categoryRepository;

    public DevBootstrap(BookcaseRepository bookcaseRepository, AuthorRepository authorRepository, BookRepository bookRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.bookcaseRepository = bookcaseRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        init();
    }

    private void init() {
        Category cat1 = new Category("Horror");
        Author author1 = new Author("Pawel", "Ograbek");
        Book book1 = new Book("Tytul", author1, 200, cat1);
        User user1 = new User("Rafal", "Wojcik","piespies", "piespies", "pies@com.pl");
        Bookcase bookcase1 = new Bookcase();
        Set<Book> books = new HashSet<>();
        books.add(book1);
        bookcase1.setBooks(books);
        bookcase1.setUser(user1);

        categoryRepository.save(cat1);
        authorRepository.save(author1);
        bookRepository.save(book1);
        userRepository.save(user1);
        bookcaseRepository.save(bookcase1);
    }
}
