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
import pl.java.ebookcase.model.Author;
import pl.java.ebookcase.model.Book;
import pl.java.ebookcase.repository.AuthorRepository;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class AuthorServiceTest {

    @Configuration
    static class AuthorServiceTestContextConfiguration {
        @Bean
        public AuthorService authorService() {
            return new AuthorService(authorRepository());
        }
        @Bean
        public AuthorRepository authorRepository() {
            return Mockito.mock(AuthorRepository.class);
        }
    }

    @Autowired
    private AuthorService authorService;
    @Autowired
    private AuthorRepository authorRepository;

    @Before
    public void setup() {
        Set<Book> books = new HashSet<>();
        Book book = new Book();
        books.add(book);
        Author author = new Author(1L, "name", "surname", books);
        Mockito.when(authorRepository.findById(1L)).thenReturn(Optional.ofNullable(author));
    }

    @Test()
    public void getAuthorByIdTest() {
        assertEquals(Long.valueOf(1L), authorService.getAuthorById(1L).getId());
    }

    @After
    public void verify() {
        Mockito.verify(authorRepository, VerificationModeFactory.times(1)).findById(Mockito.any());
        Mockito.reset(authorRepository);
    }
}