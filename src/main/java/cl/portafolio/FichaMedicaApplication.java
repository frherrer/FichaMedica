package cl.portafolio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import cl.portafolio.model.Role;
import cl.portafolio.model.User;
import cl.portafolio.repository.RoleRepository;
import cl.portafolio.repository.UserRepository;

import java.util.Set;

@SpringBootApplication
public class FichaMedicaApplication {

    public static void main(String[] args) {
        SpringApplication.run(FichaMedicaApplication.class, args);
    }

    @Bean
    CommandLineRunner init(RoleRepository roleRepo,
                           UserRepository userRepo,
                           PasswordEncoder encoder) {

        return args -> {

            if (roleRepo.count() == 0) {

                Role r1 = roleRepo.save(new Role(null, "ROLE_SECRETARIA"));
                Role r2 = roleRepo.save(new Role(null, "ROLE_MEDICO"));

                userRepo.save(new User(
                        null,
                        "secretaria",
                        encoder.encode("secret123"),
                        Set.of(r1)
                ));

                userRepo.save(new User(
                        null,
                        "medico",
                        encoder.encode("med123"),
                        Set.of(r2)
                ));
            }
        };
    }
}
