package cl.portafolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.portafolio.model.MedicalRecord;
import java.util.Optional;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    Optional<MedicalRecord> findByPatientId(Long patientId);
}
