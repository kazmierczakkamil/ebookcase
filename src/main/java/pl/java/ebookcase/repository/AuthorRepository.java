package pl.java.ebookcase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.java.ebookcase.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
