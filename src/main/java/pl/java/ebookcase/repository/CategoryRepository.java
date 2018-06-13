package pl.java.ebookcase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.java.ebookcase.model.Book;
import pl.java.ebookcase.model.Category;

import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findCategoryByBooks(Set<Book> books);
}
