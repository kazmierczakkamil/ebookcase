package pl.java.ebookcase.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.java.ebookcase.model.Book;
import pl.java.ebookcase.model.Bookcase;
import pl.java.ebookcase.repository.BookcaseRepository;

@AllArgsConstructor
@Service
public class BookcaseService {

    private BookcaseRepository bookcaseRepository;

    public Bookcase getBookcaseById(Long id) {
        return bookcaseRepository.findById(id).get();
    }

}
