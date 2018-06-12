package pl.java.ebookcase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.java.ebookcase.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
