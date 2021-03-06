package pl.java.ebookcase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.java.ebookcase.model.Bookcase;

@Repository
public interface BookcaseRepository extends JpaRepository<Bookcase, Long> {
}
