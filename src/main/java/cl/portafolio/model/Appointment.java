package cl.portafolio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Appointment() {
		super();
		// TODO Auto-generated constructor stub
	}

	@ManyToOne
    private Patient patient;

    public Appointment(Long id, Patient patient, @NotNull LocalDateTime fechaHora, String motivo) {
		super();
		this.id = id;
		this.patient = patient;
		this.fechaHora = fechaHora;
		this.motivo = motivo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	@NotNull
    private LocalDateTime fechaHora;

    private String motivo;
}
