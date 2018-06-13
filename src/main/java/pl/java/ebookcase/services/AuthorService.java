package pl.java.ebookcase.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.java.ebookcase.model.Author;
import pl.java.ebookcase.repositories.AuthorRepository;

import java.util.Optional;

//@AllArgsConstructor
@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    public Optional<Author> getAuthorById(Long id) { return authorRepository.findById(id); }
}
