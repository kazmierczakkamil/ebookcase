package pl.java.ebookcase.services;

import org.springframework.stereotype.Service;
import pl.java.ebookcase.model.Book;
import pl.java.ebookcase.repositories.BookRepository;

import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }
}
