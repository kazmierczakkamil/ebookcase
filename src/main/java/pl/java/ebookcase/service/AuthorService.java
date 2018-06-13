package pl.java.ebookcase.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.java.ebookcase.model.Author;
import pl.java.ebookcase.repository.AuthorRepository;

@AllArgsConstructor
@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).get();
    }
}
