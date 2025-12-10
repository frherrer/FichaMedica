package cl.portafolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import cl.portafolio.model.MedicalRecord;
import cl.portafolio.model.Patient;
import cl.portafolio.repository.MedicalRecordRepository;
import cl.portafolio.repository.PatientRepository;

@Controller
@RequestMapping("/medico")
public class MedicalRecordController {

    private final MedicalRecordRepository mrRepo;
    private final PatientRepository patientRepo;

    public MedicalRecordController(MedicalRecordRepository mrRepo, PatientRepository patientRepo) {
        this.mrRepo = mrRepo;
        this.patientRepo = patientRepo;
    }

    @GetMapping("/ficha/{patientId}")
    public String viewOrCreate(@PathVariable Long patientId, Model model) {
        Patient p = patientRepo.findById(patientId).orElseThrow();
        MedicalRecord mr = mrRepo.findByPatientId(patientId).orElse(new MedicalRecord());
        mr.setPatient(p);
        model.addAttribute("patient", p);
        model.addAttribute("mr", mr);
        return "medico/ficha";
    }

    @PostMapping("/ficha")
    public String save(MedicalRecord mr) {
        mrRepo.save(mr);
        return "redirect:/medico/ficha/" + mr.getPatient().getId();
    }
}
