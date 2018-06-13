package pl.java.ebookcase.services;

import org.springframework.stereotype.Service;
import pl.java.ebookcase.model.User;
import pl.java.ebookcase.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
