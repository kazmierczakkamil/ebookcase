package pl.java.ebookcase.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.java.ebookcase.model.Book;
import pl.java.ebookcase.model.Bookcase;
import pl.java.ebookcase.model.User;
import pl.java.ebookcase.repository.BookcaseRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class BookcaseServiceTest {

    @Configuration
    static class BookcaseServiceTestContextConfiguration {
        @Bean
        public BookcaseService bookcaseService() {
            return new BookcaseService(bookcaseRepository());
        }
        @Bean
        public BookcaseRepository bookcaseRepository() {
            return Mockito.mock(BookcaseRepository.class);
        }
    }

    @Autowired
    private BookcaseService bookcaseService;
    @Autowired
    private BookcaseRepository bookcaseRepository;

    @Before
    public void setup() {
        User user = new User("log", "name", "surname", "pass", "cpass", "mail");
        Set<Book> books = new HashSet<>();
        Book book = new Book();
        books.add(book);
        Bookcase bookcase = new Bookcase(1L, user, books);
        Mockito.when(bookcaseRepository.findById(1L)).thenReturn(Optional.ofNullable(bookcase));
    }

    @Test()
    public void getBookcaseByIdTest() {
        assertEquals(Long.valueOf(1L), bookcaseService.getBookcaseById(1L).getId());
    }

    @After
    public void verify() {
        Mockito.verify(bookcaseRepository, VerificationModeFactory.times(1)).findById(Mockito.any());
        Mockito.reset(bookcaseRepository);
    }
}