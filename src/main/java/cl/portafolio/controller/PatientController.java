package cl.portafolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import cl.portafolio.model.Patient;
import cl.portafolio.repository.PatientRepository;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/secretaria")
public class PatientController {

    private final PatientRepository patientRepo;

    public PatientController(PatientRepository patientRepo) {
        this.patientRepo = patientRepo;
    }

    @GetMapping("/pacientes")
    public String listPatients(Model model) {
        List<Patient> patients = patientRepo.findAll();
        model.addAttribute("patients", patients);
        return "secretaria/pacientes";
    }

    @GetMapping("/pacientes/nuevo")
    public String nuevoPacienteForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "secretaria/paciente_form";
    }

    @PostMapping("/pacientes")
    public String guardarPaciente(@Valid Patient patient, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return "secretaria/paciente_form";
        }
        patientRepo.save(patient);
        return "redirect:/secretaria/pacientes";
    }
}
