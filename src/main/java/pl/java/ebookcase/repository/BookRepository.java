package pl.java.ebookcase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.java.ebookcase.model.Author;
import pl.java.ebookcase.model.Book;
import pl.java.ebookcase.model.Category;

import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Set<Book> findBooksByAuthor(Author author);
    Set<Book> findBooksByAuthorId(Long authorId);
    Set<Book> findBooksByCategory(Category category);
    Set<Book> findBooksByCategoryId(Long categoryId);
}
