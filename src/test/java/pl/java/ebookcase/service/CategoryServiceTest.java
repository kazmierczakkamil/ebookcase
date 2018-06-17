//package pl.java.ebookcase.service;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.mockito.internal.verification.VerificationModeFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import pl.java.ebookcase.model.Author;
//import pl.java.ebookcase.model.Book;
//import pl.java.ebookcase.model.Category;
//import pl.java.ebookcase.repository.BookRepository;
//import pl.java.ebookcase.repository.CategoryRepository;
//
//import java.util.*;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
//public class CategoryServiceTest {
//
//    @Configuration
//    static class CategoryServiceContextConfiguration {
//        @Bean
//        public CategoryService categoryService() {
//            return new CategoryService(categoryRepository());
//        }
//        @Bean
//        public CategoryRepository categoryRepository() {
//            return Mockito.mock(CategoryRepository.class);
//        }
//        @Bean
//        public BookService bookService() {
//            return new BookService(bookRepository());
//        }
//        @Bean
//        public BookRepository bookRepository() {
//            return Mockito.mock(BookRepository.class);
//        }
//    }
//
//    @Autowired
//    private CategoryService categoryService;
//    @Autowired
//    private CategoryRepository categoryRepository;
//    @Autowired
//    private BookService bookService;
//    @Autowired
//    private BookRepository bookRepository;
//
//    @Before
//    public void setup() {
//        Author author = new Author("name", "surname");
//        author.setId(1L);
//        Set<Book> books = new HashSet<>();
//        Book book = new Book();
//        book.setId(11L);
//        book.setAuthor(author);
//        books.add(book);
//        Category category = new Category(111L, "kat", books);
//        List<Category> categoryList = new ArrayList<>();
//        categoryList.add(category);
//        Mockito.when(categoryRepository.findById(111L)).thenReturn(Optional.ofNullable(category));
//        Mockito.when(categoryRepository.findCategoryByBooks(books)).thenReturn(category);
//        Mockito.when(bookRepository.findBooksByAuthorId(1L)).thenReturn(books);
//        Mockito.when(categoryRepository.findAll()).thenReturn(categoryList);
//    }
//
//    @Test()
//    public void getCategoryByIdTest() {
//        assertEquals(Long.valueOf(111L), categoryService.getCategoryById(111L).getId());
//        assertEquals("kat", categoryService.getCategoryById(111L).getName());
//        Mockito.verify(categoryRepository, VerificationModeFactory.times(2)).findById(Mockito.any());
//    }
//
//    @Test()
//    public void getCategoryByBooksTest() {
//        Set<Book> bookSet = bookService.getBooksByAuthorId(1L);
//        assertEquals(Long.valueOf(111L), categoryService.getCategoryByBooks(bookSet).getId());
//        assertEquals("kat", categoryService.getCategoryByBooks(bookSet).getName());
//        Mockito.verify(bookRepository, VerificationModeFactory.times(1)).findBooksByAuthorId(Mockito.any());
//        Mockito.verify(categoryRepository, VerificationModeFactory.times(2)).findCategoryByBooks(Mockito.any());
//    }
//
//    @Test()
//    public void getCategoriesTest() {
//        List<Category> categoryList = new ArrayList<>(categoryService.getCategories());
//        assertEquals(Long.valueOf(111L), categoryList.get(0).getId());
//        assertEquals("kat", categoryList.get(0).getName());
//        Mockito.verify(categoryRepository, VerificationModeFactory.times(1)).findAll();
//    }
//
//    @After
//    public void reset() {
//        Mockito.reset(categoryRepository);
//        Mockito.reset(bookRepository);
//    }
//}