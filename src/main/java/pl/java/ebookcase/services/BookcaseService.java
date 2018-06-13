package pl.java.ebookcase.services;

import org.springframework.stereotype.Service;
import pl.java.ebookcase.model.Bookcase;
import pl.java.ebookcase.repositories.BookcaseRepository;

import java.util.Optional;

@Service
public class BookcaseService {

    private BookcaseRepository bookcaseRepository;

    public Optional<Bookcase> getBookcaseById(Long id) { return bookcaseRepository.findById(id); }
}
