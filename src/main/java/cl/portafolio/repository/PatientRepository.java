package cl.portafolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.portafolio.model.Patient;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByRut(String rut);
}
