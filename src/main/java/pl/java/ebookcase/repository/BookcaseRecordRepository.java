package pl.java.ebookcase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.java.ebookcase.model.Book;
import pl.java.ebookcase.model.Bookcase;
import pl.java.ebookcase.model.BookcaseRecord;
import pl.java.ebookcase.model.User;

import java.util.List;

@Repository
public interface BookcaseRecordRepository extends JpaRepository<BookcaseRecord, Long> {

    List<BookcaseRecord> findAllByBookcase_User(User user);
    Boolean existsAllByBookAndBookcase_User(Book book, User user);
    BookcaseRecord findByBookAndAndBookcase_User(Book book, User user);
}
