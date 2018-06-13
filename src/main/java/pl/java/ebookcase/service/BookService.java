package pl.java.ebookcase.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.java.ebookcase.model.Book;
import pl.java.ebookcase.repository.BookRepository;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Service
public class BookService {

    private BookRepository bookRepository;

    public Book getBookById(Long id) {
        return bookRepository.findById(id).get();
    }

    public Set<Book> getBooksByCategoryId(Long id) {
        return bookRepository.findBooksByCategoryId(id);
    }

    public Set<Book> getBooksByAuthorId(Long authorId) {
        return bookRepository.findBooksByAuthorId(authorId);
    }

    public Set<Book> getBooksById(Set<Long> bookIds) {
        return new HashSet<>(bookRepository.findAllById(bookIds));
    }
}
