package pl.java.ebookcase.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.java.ebookcase.model.Review;
import pl.java.ebookcase.repository.ReviewRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    public Review save(Review review) {
        return  reviewRepository.save(review);
    }
    public List<Review> getReviewsByBookId(Long bookId) { return reviewRepository.findAllByBook_Id(bookId); }
}
