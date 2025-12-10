package cl.portafolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.portafolio.model.Role;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
