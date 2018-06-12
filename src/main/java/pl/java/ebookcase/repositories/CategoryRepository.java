package pl.java.ebookcase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.java.ebookcase.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
