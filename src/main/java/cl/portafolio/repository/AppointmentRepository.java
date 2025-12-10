package cl.portafolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.portafolio.model.Appointment;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByFechaHoraBetween(LocalDateTime start, LocalDateTime end);
}
