package pl.java.ebookcase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.java.ebookcase.model.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByBook_Id(Long bookId);
}
