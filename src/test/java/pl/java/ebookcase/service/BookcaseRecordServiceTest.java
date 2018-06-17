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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.java.ebookcase.model.*;
import pl.java.ebookcase.repository.BookRepository;
import pl.java.ebookcase.repository.BookcaseRecordRepository;
import pl.java.ebookcase.repository.BookcaseRepository;
import pl.java.ebookcase.repository.UserRepository;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class BookcaseRecordServiceTest {

    @Configuration
    static class BookcaseRecordServiceContextConfiguration {
        @Bean
        public BookcaseRecordService bookcaseRecordService() {
            return new BookcaseRecordService(bookcaseRecordRepository());
        }
        @Bean
        public BookcaseRecordRepository bookcaseRecordRepository() {
            return Mockito.mock(BookcaseRecordRepository.class);
        }
        @Bean
        public UserService userService() {
            return new UserService(userRepository(),bookcaseRepository(), bCryptPasswordEncoder());
        }
        @Bean
        public UserRepository userRepository() {
            return Mockito.mock(UserRepository.class);
        }
        @Bean
        public BCryptPasswordEncoder bCryptPasswordEncoder() {
            return Mockito.mock(BCryptPasswordEncoder.class);
        }
        @Bean
        public BookcaseService bookcaseService() {
            return new BookcaseService(bookcaseRepository());
        }
        @Bean
        public BookcaseRepository bookcaseRepository() {
            return Mockito.mock(BookcaseRepository.class);
        }
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
    private BookcaseRecordService bookcaseRecordService;
    @Autowired
    private BookcaseRecordRepository bookcaseRecordRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;

    @Before
    public void setup() {
        Author author = new Author("cos", "ktos");
        Category category = new Category("kat");
        Book book = new Book("jakas", author, 10, category);
        book.setId(111L);
        User user = new User("log", "n", "s", "123", "123", "@");
        user.setId(1L);
        Bookcase bookcase = new Bookcase();
        bookcase.setId(11L);
        bookcase.setUser(user);
        BookcaseRecord bookcaseRecord = new BookcaseRecord(book, bookcase);
        bookcaseRecord.setId(1111L);
        bookcaseRecord.setBook(book);
        bookcaseRecord.setBookcase(user.getBookcase());
        Set<BookcaseRecord> bookcaseRecordSet = new HashSet<>();
        bookcaseRecordSet.add(bookcaseRecord);
        bookcase.setRecords(bookcaseRecordSet);
        List<BookcaseRecord> bookcaseRecordList = new ArrayList<>();
        bookcaseRecordList.add(bookcaseRecord);
        Mockito.when(bookcaseRecordRepository.findAll()).thenReturn(bookcaseRecordList);
        Mockito.when(bookcaseRecordRepository.findAllByBookcase_User(user)).thenReturn(bookcaseRecordList);
        Mockito.when(bookcaseRecordRepository.existsAllByBookAndBookcase_User(book, user)).thenReturn(true);
        Mockito.when(bookcaseRecordRepository.save(bookcaseRecord)).thenReturn(bookcaseRecord);
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
        Mockito.when(bookRepository.findById(111L)).thenReturn(Optional.ofNullable(book));
    }

    @Test()
    public void getAllTest() {
        List<BookcaseRecord> bookcaseRecordsList = bookcaseRecordService.getAll();
        Mockito.verify(bookcaseRecordRepository, VerificationModeFactory.times(1)).findAll();
        assertEquals(Long.valueOf(1111L), bookcaseRecordsList.get(0).getId());
        assertNotNull(bookcaseRecordsList.get(0).getDateAdded());
    }

    @Test()
    public void getBookcaseRecordsByUserTest() {
        User user = userService.getUserById(1L);
        Mockito.verify(userRepository, VerificationModeFactory.times(1)).findById(Mockito.any());
        List<BookcaseRecord> bookcaseRecordsList = bookcaseRecordService.getBookcaseRecordsByUser(user);
        Mockito.verify(bookcaseRecordRepository, VerificationModeFactory.times(1)).findAllByBookcase_User(Mockito.any());
        assertEquals(Long.valueOf(1111L), bookcaseRecordsList.get(0).getId());
        assertEquals(user.getBookcase(), bookcaseRecordsList.get(0).getBookcase());
        assertNotNull(bookcaseRecordsList.get(0).getDateAdded());
    }

    @Test()
    public void isUserBookcaseContainsBookTest() {
        Book book = bookService.getBookById(111L);
        Mockito.verify(bookRepository, VerificationModeFactory.times(1)).findById(Mockito.any());
        User user = userService.getUserById(1L);
        Mockito.verify(userRepository, VerificationModeFactory.times(1)).findById(Mockito.any());
        assertTrue(bookcaseRecordService.isUserBookcaseContainsBook(book, user));
        Mockito.verify(bookcaseRecordRepository, VerificationModeFactory.times(1)).existsAllByBookAndBookcase_User(book, user);
    }

    @Test()
    public void saveTest() {
        Book book = bookService.getBookById(111L);
        Mockito.verify(bookRepository, VerificationModeFactory.times(1)).findById(Mockito.any());
        User user = userService.getUserById(1L);
        Mockito.verify(userRepository, VerificationModeFactory.times(1)).findById(Mockito.any());
        BookcaseRecord bookcaseRecordSave = bookcaseRecordService.save(book, user);
        Mockito.verify(bookcaseRecordRepository, VerificationModeFactory.times(1)).save(Mockito.any());
}

    @Test()
    public void removeTest() {
        List<BookcaseRecord> bookcaseRecordList = bookcaseRecordService.getAll();
        Book book = bookService.getBookById(111L);
        Mockito.verify(bookRepository, VerificationModeFactory.times(1)).findById(Mockito.any());
        User user = userService.getUserById(1L);
        Mockito.verify(userRepository, VerificationModeFactory.times(1)).findById(Mockito.any());
        bookcaseRecordService.remove(book, user);
        Mockito.verify(bookcaseRecordRepository, VerificationModeFactory.times(1)).delete(Mockito.any());
    }

    @After
    public void reset() {
        Mockito.reset(bookcaseRecordRepository);
        Mockito.reset(bookRepository);
        Mockito.reset(userRepository);
    }
}