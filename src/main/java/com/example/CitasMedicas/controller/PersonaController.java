package com.example.CitasMedicas.controller;

import com.example.CitasMedicas.model.Persona;
import com.example.CitasMedicas.repository.PersonaRepository;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/api/personas"})
public class PersonaController {
    @Autowired
    PersonaRepository personaRepository;

    public PersonaController() {
    }

    public int calcularEdad(Date fechaNacimiento) {
        LocalDate fechaNacimientoLocal = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fechaActual = LocalDate.now();
        Period periodo = Period.between(fechaNacimientoLocal, fechaActual);
        return periodo.getYears();
    }

    @GetMapping({"/perfil/{id}"})
    public String editarr(@PathVariable Long id, Model model) {
        Optional<Persona> persona = this.personaRepository.findById(id);
        if (persona.isPresent()) {
            model.addAttribute("persona", persona.get());
            System.out.println(persona);
        } else {
            model.addAttribute("persona", (Object)null);
        }

        return "mi_perfil";
    }
}
