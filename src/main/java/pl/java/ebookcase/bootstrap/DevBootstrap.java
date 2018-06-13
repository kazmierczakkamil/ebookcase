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

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        init();
    }

    private void init() {
        Category cat1 = new Category("Horror");
        Category cat2 = new Category("Komedia");
        Category cat3 = new Category("Sensacja");
        Author author1 = new Author("Pawel", "Ograbek");
        Book book1 = new Book("Tytul", author1, 200, cat1);
        User user1 = new User("some", "Rafal", "Wojcik", "piespies", "piespies", "pies@com.pl");
        Bookcase bookcase1 = new Bookcase();
        bookcase1.getBooks().add(book1);
        bookcase1.setUser(user1);

        categoryRepository.save(cat1);
        categoryRepository.save(cat2);
        categoryRepository.save(cat3);
        authorRepository.save(author1);
        bookRepository.save(book1);
        userService.save(user1);
        bookcaseRepository.save(bookcase1);
    }
}
