package com.example.CitasMedicas.controller;

import com.example.CitasMedicas.model.Consulta;
import com.example.CitasMedicas.model.Paciente;
import com.example.CitasMedicas.repository.ConsultaRepository;
import com.example.CitasMedicas.service.PacienteService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping({"/api/consultas"})
public class ConsultaController {
    @Autowired
    ConsultaRepository consultaRepository;
    @Autowired
    PacienteService pacienteService;

    public ConsultaController() {
    }

    @GetMapping({"/nuevo"})
    public String nuevo() {
        return "consulta";
    }

    @GetMapping
    public String listar(Model model) {
        List<Paciente> pacientes = this.pacienteService.listar();
        model.addAttribute("paciente", new Paciente());
        model.addAttribute("pacientes", pacientes);
        return "consulta";
    }

    @GetMapping({"/eliminar/{id}"})
    public String eliminar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Consulta> consultaExistente = this.consultaRepository.findById(id);
        if (consultaExistente.isPresent()) {
            Consulta consulta = (Consulta)consultaExistente.get();
            this.consultaRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("mensaje", "La consulta se elimino con éxito");
            redirectAttributes.addAttribute("pacienteId", consulta.getPaciente().getId());
        } else {
            redirectAttributes.addFlashAttribute("error", "La consulta se elimino correctamente");
        }

        return "redirect:/api/historias_clinicas/nuevo";
    }
}
