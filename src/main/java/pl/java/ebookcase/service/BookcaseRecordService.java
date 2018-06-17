package pl.java.ebookcase.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.java.ebookcase.model.Book;
import pl.java.ebookcase.model.Bookcase;
import pl.java.ebookcase.model.BookcaseRecord;
import pl.java.ebookcase.model.User;
import pl.java.ebookcase.repository.BookcaseRecordRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class BookcaseRecordService {

    private BookcaseRecordRepository bookcaseRecordRepository;

    public List<BookcaseRecord> getAll() {
        return bookcaseRecordRepository.findAll();
    }

    public List<BookcaseRecord> getBookcaseRecordsByUser(User user) {
        return bookcaseRecordRepository.findAllByBookcase_User(user);
    }

    public Boolean isUserBookcaseContainsBook(Book book, User user) {
        return bookcaseRecordRepository.existsAllByBookAndBookcase_User(book, user);
    }

    public BookcaseRecord save(Book book, User user) {
        BookcaseRecord bookcaseRecord = new BookcaseRecord();
        bookcaseRecord.setBook(book);
        bookcaseRecord.setBookcase(user.getBookcase());
        return bookcaseRecordRepository.save(bookcaseRecord);
    }

    public void remove(Book book, User user) {
        BookcaseRecord record = bookcaseRecordRepository.findByBookAndAndBookcase_User(book, user);
        bookcaseRecordRepository.delete(record);
    }
}
