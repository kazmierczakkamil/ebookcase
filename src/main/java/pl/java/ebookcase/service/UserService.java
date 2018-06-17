package pl.java.ebookcase.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.java.ebookcase.model.Book;
import pl.java.ebookcase.model.Bookcase;
import pl.java.ebookcase.model.BookcaseRecord;
import pl.java.ebookcase.model.User;
import pl.java.ebookcase.repository.BookcaseRepository;
import pl.java.ebookcase.repository.UserRepository;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;
    private BookcaseRepository bookcaseRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }
    public User save(User user) {
        user.setPasswordEncrypted(bCryptPasswordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        Bookcase bookcase = new Bookcase();
        bookcase.setUser(user);
        bookcaseRepository.save(bookcase);
        return user;
    }

    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

}
