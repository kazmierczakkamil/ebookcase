package pl.java.ebookcase.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.java.ebookcase.model.Book;
import pl.java.ebookcase.repository.BookRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class BookService {

    private BookRepository bookRepository;


    public List<Book> getBooks() { return bookRepository.findAll(); }
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

    public List<Book> getBooksListByAuthorId(Long authorId) {
        return bookRepository.findBooksListByAuthorId(authorId);
    }

    public List<Book> getBooksListByCategoryId(Long categoryId) {
        return bookRepository.findBooksListByCategoryId(categoryId);
    }
}
