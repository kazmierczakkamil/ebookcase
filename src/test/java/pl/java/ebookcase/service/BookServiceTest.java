package pl.java.ebookcase.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.java.ebookcase.model.Author;
import pl.java.ebookcase.model.Book;
import pl.java.ebookcase.model.Category;
import pl.java.ebookcase.repository.BookRepository;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class BookServiceTest {

    @Configuration
    static class BookServiceContextConfiguration {
        @Bean
        public BookService bookService() {
            return new BookService(bookRepository());
        }
        @Bean
        public BookRepository bookRepository() {
            return Mockito.mock(BookRepository.class);
        }
    }

    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;

    @Before
    public void setup() {
        Author author = new Author("name", "surname");
        author.setId(1L);
        Category category = new Category("kat");
        category.setId(11L);
        Set<Book> books = new HashSet<>();
        Book book = new Book(111L,"jakas",10 , category, author);
        books.add(book);
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        Set<Long> booksId = new HashSet<>();
        booksId.add(book.getId());
        Mockito.when(bookRepository.findAll()).thenReturn(bookList);
        Mockito.when(bookRepository.findById(111L)).thenReturn(Optional.ofNullable(book));
        Mockito.when(bookRepository.findBooksByCategoryId(11L)).thenReturn(books);
        Mockito.when(bookRepository.findBooksByAuthorId(1L)).thenReturn(books);
        Mockito.when(bookRepository.findAllById(booksId)).thenReturn(bookList);
    }

    @Test()
    public void getBooksTest() {
        assertEquals(Long.valueOf(111L), bookService.getBooks().get(0).getId());
        assertEquals("jakas", bookService.getBooks().get(0).getTitle());
    }

    @Test()
    public void getBookByIdTest() {
        assertEquals(Long.valueOf(111L), bookService.getBookById(111L).getId());
        assertEquals("jakas", bookService.getBookById(111L).getTitle());
    }

    @Test()
    public void getBooksByCategoryIdTest() {
        List<Book> bookList = new ArrayList<>(bookService.getBooksByCategoryId(11L));
        assertEquals(Long.valueOf(111L), bookList.get(0).getId());
        assertEquals("jakas", bookList.get(0).getTitle());
    }

    @Test()
    public void getBooksByAuthorIdTest() {
        List<Book> bookList = new ArrayList<>(bookService.getBooksByAuthorId(1L));
        assertEquals(Long.valueOf(111L), bookList.get(0).getId());
        assertEquals("jakas", bookList.get(0).getTitle());
    }

    @Test()
    public void getBooksByIdTest() {
        Set<Long> booksId = new HashSet<>();
        booksId.add(111L);
        List<Book> bookList = new ArrayList<>(bookService.getBooksById(booksId));
        assertEquals(Long.valueOf(111L), bookList.get(0).getId());
        assertEquals("jakas", bookList.get(0).getTitle());
    }

    @After
    public void reset() {
        Mockito.reset(bookRepository);
    }
}