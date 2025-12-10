package cl.portafolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import cl.portafolio.model.Appointment;
import cl.portafolio.model.Patient;
import cl.portafolio.repository.AppointmentRepository;
import cl.portafolio.repository.PatientRepository;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/secretaria")
public class AppointmentController {

    private final AppointmentRepository apptRepo;
    private final PatientRepository patientRepo;

    public AppointmentController(AppointmentRepository apptRepo, PatientRepository patientRepo) {
        this.apptRepo = apptRepo;
        this.patientRepo = patientRepo;
    }

    @GetMapping("/citas")
    public String listTodayAppointments(Model model) {
        LocalDate today = LocalDate.now();
        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.atTime(LocalTime.MAX);
        List<Appointment> citas = apptRepo.findByFechaHoraBetween(start, end);
        model.addAttribute("citas", citas);
        model.addAttribute("patients", patientRepo.findAll());
        model.addAttribute("newAppointment", new Appointment());
        return "secretaria/citas";
    }

    @PostMapping("/citas")
    public String crearCita(@Valid Appointment appointment, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return "secretaria/citas";
        }
        if (appointment.getPatient() != null && appointment.getPatient().getId() != null) {
            Patient p = patientRepo.findById(appointment.getPatient().getId()).orElse(null);
            appointment.setPatient(p);
        }
        apptRepo.save(appointment);
        return "redirect:/secretaria/citas";
    }
}
