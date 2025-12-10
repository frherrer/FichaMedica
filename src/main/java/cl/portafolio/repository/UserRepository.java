package cl.portafolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.portafolio.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
