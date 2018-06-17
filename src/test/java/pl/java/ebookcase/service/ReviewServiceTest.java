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
import pl.java.ebookcase.model.*;
import pl.java.ebookcase.repository.ReviewRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ReviewServiceTest {

    @Configuration
    static class ReviewServiceContextConfiguration {
        @Bean
        public ReviewService reviewService() {
            return new ReviewService(reviewRepository());
        }
        @Bean
        public ReviewRepository reviewRepository() {
            return Mockito.mock(ReviewRepository.class);
        }
    }

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ReviewRepository reviewRepository;

    @Before
    public void setup() {
        Author author = new Author("cos", "ktos");
        Category category = new Category("kat");
        Book book = new Book("jakas", author, 10, category);
        book.setId(1L);
        User user = new User("log", "name", "surname", "pass", "cpass", "mail");
        Review review = new Review("opis", book, user);
        review.setId(11L);
        List<Review> reviewList = new ArrayList<>();
        reviewList.add(review);
        Mockito.when(reviewRepository.save(review)).thenReturn(review);
        Mockito.when(reviewRepository.findAllByBook_Id(1L)).thenReturn(reviewList);
    }

    @Test()
    public void saveTest() {
        List<Review> reviewList = reviewService.getReviewsByBookId(1L);
        Mockito.verify(reviewRepository, VerificationModeFactory.times(1)).findAllByBook_Id(Mockito.any());
        Review review = reviewService.save(reviewList.get(0));
        Mockito.verify(reviewRepository, VerificationModeFactory.times(1)).save(Mockito.any());
        assertEquals(Long.valueOf(11L),review.getId());
        assertEquals("opis",review.getContent());
        assertEquals(Long.valueOf(1L),review.getBook().getId());
        assertEquals("name",review.getUser().getName());
    }

    @Test()
    public void getReviewsByBookIdTest() {
        List<Review> reviewList = reviewService.getReviewsByBookId(1L);
        Mockito.verify(reviewRepository, VerificationModeFactory.times(1)).findAllByBook_Id(Mockito.any());
        assertEquals(Long.valueOf(11L),reviewList.get(0).getId());
        assertEquals("opis",reviewList.get(0).getContent());
        assertEquals(Long.valueOf(1L),reviewList.get(0).getBook().getId());
        assertEquals("name",reviewList.get(0).getUser().getName());
    }

    @After
    public void reset() {
        Mockito.reset(reviewRepository);
    }
}