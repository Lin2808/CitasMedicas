package com.example.CitasMedicas.controller;

import com.example.CitasMedicas.model.AntecedentesPatologicosFamiliares;
import com.example.CitasMedicas.model.Consulta;
import com.example.CitasMedicas.model.Paciente;
import com.example.CitasMedicas.model.Persona;
import com.example.CitasMedicas.repository.AntecedentesPatologicosFamiliaresRepository;
import com.example.CitasMedicas.repository.ConsultaRepository;
import com.example.CitasMedicas.repository.PacienteRepository;
import com.example.CitasMedicas.repository.PersonaRepository;
import com.example.CitasMedicas.service.PacienteService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping({"/api/pacientes"})
public class PacienteController {
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    ConsultaRepository consultaRepository;
    @Autowired
    PacienteService pacienteService;
    @Autowired
    AntecedentesPatologicosFamiliaresRepository antecedentesRepository;

    public PacienteController() {
    }

    @GetMapping
    public String mostrarFormularioNuevoPaciente(Model model) {
        Paciente paciente = new Paciente();
        List<Paciente> pacientes = this.pacienteService.listar();
        new Persona();
        model.addAttribute("pacientes", pacientes);
        model.addAttribute("paciente", paciente);
        return "pacientes";
    }

    @PostMapping({"/save"})
    public String guardarPaciente(@ModelAttribute("paciente") Paciente paciente, RedirectAttributes attributes) {
        Persona persona = new Persona();
        persona.setApellidos(paciente.getPersona().getApellidos());
        persona.setNombres(paciente.getPersona().getNombres());
        persona.setDireccion(paciente.getPersona().getDireccion());
        persona.setSexo(paciente.getPersona().getSexo());
        persona.setCedula(paciente.getPersona().getCedula());
        persona.setTelefono(paciente.getPersona().getTelefono());
        persona.setFechaNacimiento(paciente.getPersona().getFechaNacimiento());
        this.personaRepository.save(persona);
        paciente.setEstado(true);
        paciente.setPersona(persona);
        this.pacienteRepository.save(paciente);
        attributes.addFlashAttribute("mensaje", "Paciente registrado correctamente");
        return "redirect:/api/consultas";
    }

    @PostMapping({"/update/{id}"})
    public String updatePaciente(@ModelAttribute("paciente") Paciente paciente, @PathVariable Long id) {
        new Persona();
        Persona personaExistente = (Persona)this.personaRepository.findById(paciente.getPersona().getId()).get();
        personaExistente.setId(paciente.getPersona().getId());
        personaExistente.setApellidos(paciente.getPersona().getApellidos());
        this.personaRepository.save(personaExistente);
        paciente.setEstado(true);
        paciente.setId(id);
        paciente.setPersona(personaExistente);
        this.pacienteRepository.save(paciente);
        return "redirect:/api/consultas";
    }

    @PostMapping({"/updatee"})
    public String actualizarPaciente(@ModelAttribute("paciente") Paciente paciente) {
        Persona persona = new Persona();
        persona.setApellidos(paciente.getPersona().getApellidos());
        persona.setNombres(paciente.getPersona().getNombres());
        persona.setDireccion(paciente.getPersona().getDireccion());
        persona.setSexo(paciente.getPersona().getSexo());
        persona.setCedula(paciente.getPersona().getCedula());
        persona.setTelefono(paciente.getPersona().getTelefono());
        persona.setFechaNacimiento(paciente.getPersona().getFechaNacimiento());
        this.personaRepository.save(persona);
        paciente.setEstado(true);
        paciente.setPersona(persona);
        this.pacienteRepository.save(paciente);
        return "redirect:/api/consultas";
    }

    @PostMapping({"/editarr/{id}"})
    public String editar(Model model, @PathVariable("id") Long id) {
        model.addAttribute("paciente", this.pacienteRepository.findById(id).get());
        return "editar_paciente";
    }

    @GetMapping({"/update"})
    public String updatePaciente(@ModelAttribute("paciente") Paciente paciente) {
        return "editar_paciente";
    }

    @PostMapping({"/buscar"})
    public String buscarPorNombreOrApellido(@RequestParam("nombreApellido") String nombreApellido, Model model) {
        List<Paciente> pacientes = this.pacienteService.buscarPorNombresOApellidos(nombreApellido, nombreApellido, nombreApellido);
        model.addAttribute("pacientes", pacientes);
        return "consulta";
    }

    @PostMapping({"/update"})
    public String updatePaciente(@ModelAttribute("paciente") Paciente paciente, RedirectAttributes attributes) {
        Optional<Paciente> optionalPacienteExistente = this.pacienteRepository.findById(paciente.getId());
        if (optionalPacienteExistente.isPresent()) {
            Paciente pacienteExistente = (Paciente)optionalPacienteExistente.get();
            if (!paciente.getPersona().getCedula().equals(pacienteExistente.getPersona().getCedula())) {
                pacienteExistente.getPersona().setCedula(paciente.getPersona().getCedula());
            }

            Persona personaExistente = pacienteExistente.getPersona();
            personaExistente.setNombres(paciente.getPersona().getNombres());
            personaExistente.setApellidos(paciente.getPersona().getApellidos());
            personaExistente.setDireccion(paciente.getPersona().getDireccion());
            personaExistente.setSexo(paciente.getPersona().getSexo());
            personaExistente.setFechaNacimiento(paciente.getPersona().getFechaNacimiento());
            pacienteExistente.setAntecedentesPersonales(paciente.getAntecedentesPersonales());
            pacienteExistente.setPersona(personaExistente);
            this.pacienteRepository.save(pacienteExistente);
            attributes.addFlashAttribute("mensaje", "El paciente se ha actualizado correctamente");
        } else {
            attributes.addFlashAttribute("error", "El paciente no existe en la base de datos");
        }

        return "redirect:/api/pacientes";
    }

    @GetMapping({"/buscar/{id}"})
    public String findById(@PathVariable Long id, Model model) {
        Optional<Paciente> paciente = this.pacienteService.findById(id);
        if (paciente.isPresent()) {
            model.addAttribute("pacienteEncontrado", paciente.get());
        } else {
            model.addAttribute("pacienteEncontrado", (Object)null);
        }

        return "resultadosBusqueda";
    }

    @GetMapping({"/editar/{id}"})
    public String editarr(@PathVariable Long id, Model model) {
        Optional<Paciente> paciente = this.pacienteService.findById(id);
        if (paciente.isPresent()) {
            model.addAttribute("paciente", paciente.get());
        } else {
            model.addAttribute("paciente", (Object)null);
        }

        return "editar_paciente";
    }

    @GetMapping({"/edit"})
    public String edit() {
        return "editar_paciente";
    }

    @GetMapping({"/eliminar/{id}"})
    public String eliminarPersona(@PathVariable Long id, Model model) {
        model.addAttribute("paciente", this.pacienteRepository.findById(id).get());
        return "eliminar_paciente";
    }

    @GetMapping({"/eliminarPaciente/{id}"})
    public String eliminarPaciente(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Paciente> pacienteOptional = this.pacienteRepository.findById(id);
        if (pacienteOptional.isPresent()) {
            Paciente paciente = (Paciente)pacienteOptional.get();
            if (this.tieneRelacionesConOtrasEntidades(paciente)) {
                redirectAttributes.addFlashAttribute("error", "No se puede eliminar el paciente debido a tiene registrado consultas previas.");
            } else if (this.tieneRelacionesConOtrasEntidades2(paciente)) {
                redirectAttributes.addFlashAttribute("error", "No se puede eliminar el paciente debido a tiene antecedentes patológicos registrados.");
            } else {
                this.pacienteRepository.deleteById(id);
                redirectAttributes.addAttribute("mensaje", "El paciente se ha eliminado correctamente");
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Paciente no encontrado.");
        }

        return "redirect:/api/pacientes";
    }

    private boolean tieneRelacionesConOtrasEntidades(Paciente paciente) {
        List<Consulta> consultas = this.consultaRepository.findByPaciente(paciente);
        return !consultas.isEmpty();
    }

    private boolean tieneRelacionesConOtrasEntidades2(Paciente paciente) {
        List<AntecedentesPatologicosFamiliares> antecedentes = this.antecedentesRepository.findByPaciente(paciente);
        return !antecedentes.isEmpty();
    }
}
