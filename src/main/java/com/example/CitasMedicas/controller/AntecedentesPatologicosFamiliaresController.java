package com.example.CitasMedicas.controller;

import com.example.CitasMedicas.model.AntecedentesPatologicosFamiliares;
import com.example.CitasMedicas.model.Paciente;
import com.example.CitasMedicas.repository.AntecedentesPatologicosFamiliaresRepository;
import com.example.CitasMedicas.repository.PacienteRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping({"/api/antecedentes"})
public class AntecedentesPatologicosFamiliaresController {
    @Autowired
    AntecedentesPatologicosFamiliaresRepository antecedentesRepository;
    @Autowired
    PacienteRepository pacienteRepository;

    public AntecedentesPatologicosFamiliaresController() {
    }

    @PostMapping({"/save/{id}"})
    public String guardarPaciente(@ModelAttribute("antecedentes") AntecedentesPatologicosFamiliares antecedentes, @PathVariable("id") Long id, RedirectAttributes attributes) {
        Optional<Paciente> pacienteOptional = this.pacienteRepository.findById(id);
        antecedentes.setPaciente((Paciente)pacienteOptional.get());
        this.antecedentesRepository.save(antecedentes);
        attributes.addAttribute("pacienteId", id);
        return "redirect:/api/historias_clinicas/nuevo";
    }

    @GetMapping({"/update/{id}"})
    public String guardarPaciente(@PathVariable("id") Long id, RedirectAttributes attributes, Model model) {
        Optional<AntecedentesPatologicosFamiliares> antecedente = this.antecedentesRepository.findById(id);
        model.addAttribute("antecedente", antecedente.get());
        return "editar_antecedente";
    }

    @PostMapping({"/update/{id}"})
    public String updatePaciente(@ModelAttribute("antecedente") AntecedentesPatologicosFamiliares antecedente, @PathVariable Long id, RedirectAttributes attributes) {
        new AntecedentesPatologicosFamiliares();
        AntecedentesPatologicosFamiliares antecedenteExistente = (AntecedentesPatologicosFamiliares)this.antecedentesRepository.findById(antecedente.getId()).get();
        antecedenteExistente.setPatologia(antecedente.getPatologia());
        antecedenteExistente.setAlergias(antecedente.getAlergias());
        antecedenteExistente.setId(antecedente.getId());
        Long idPaciente = antecedenteExistente.getPaciente().getId();
        attributes.addAttribute("pacienteId", idPaciente);
        this.antecedentesRepository.save(antecedenteExistente);
        return "redirect:/api/historias_clinicas/nuevo";
    }

    @GetMapping({"/delete/{id}"})
    public String eliminarUsuario(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes)
    {
        Optional<AntecedentesPatologicosFamiliares> antecedenteOptional = this.antecedentesRepository.findById(id);
        Long idPaciente = ((AntecedentesPatologicosFamiliares)antecedenteOptional.get()).getPaciente().getId();
        redirectAttributes.addAttribute("pacienteId", idPaciente);
        if (antecedenteOptional.isPresent())
        {
            this.antecedentesRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("mensaje", "El antecedente se ha eliminado correctamente");
        }
        else
        {
            redirectAttributes.addFlashAttribute("error", "Antecedente no encontrado.");
        }

        return "redirect:/api/historias_clinicas/nuevo";
    }
}
