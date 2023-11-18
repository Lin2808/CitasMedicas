package com.example.CitasMedicas.controller;

import com.example.CitasMedicas.model.AntecedentesPatologicosFamiliares;
import com.example.CitasMedicas.model.Consulta;
import com.example.CitasMedicas.model.Paciente;
import com.example.CitasMedicas.repository.AntecedentesPatologicosFamiliaresRepository;
import com.example.CitasMedicas.repository.ConsultaRepository;
import com.example.CitasMedicas.service.PacienteService;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

@Controller
@RequestMapping({"/api/historias_clinicas"})
public class HistoriaClinicaController {
    @Autowired
    PacienteService pacienteService;
    public Long id;
    @Autowired
    AntecedentesPatologicosFamiliaresRepository antecedentesRepository;
    @Autowired
    ConsultaRepository consultaRepository;
    public Paciente pacienteTEMP = new Paciente();

    public HistoriaClinicaController() {
    }

    @GetMapping({"/nuevo"})
    public String nuevaHistoria(@RequestParam("pacienteId") Long pacienteId, Model model) {
        Paciente paciente = (Paciente)this.pacienteService.findById(pacienteId).orElse((Paciente) null);
        if (paciente != null) {
            model.addAttribute("paciente", paciente);
            this.pacienteTEMP = paciente;
            this.id = pacienteId;
            int edad = this.calcularEdad((Date)paciente.getPersona().getFechaNacimiento());
            model.addAttribute("edad", edad);
            Consulta consulta = new Consulta();
            consulta.setPaciente(paciente);
            model.addAttribute("consulta", consulta);
            System.out.println(consulta);
            List<AntecedentesPatologicosFamiliares> antecedentes = this.antecedentesRepository.findByPaciente_Id(paciente.getId());
            model.addAttribute("antecedentes", antecedentes);
            List<Consulta> consultas = this.consultaRepository.findByPaciente_Id(paciente.getId());
            model.addAttribute("consultas", consultas);
            return "historia_clinica";
        } else {
            return "error_page";
        }
    }

    public int calcularEdad(Date fechaNacimientoSQL) {
        LocalDate fechaNacimientoLocal = fechaNacimientoSQL.toLocalDate();
        LocalDate fechaActualLocal = LocalDate.now();
        int edad = Period.between(fechaNacimientoLocal, fechaActualLocal).getYears();
        return edad;
    }

    @PostMapping({"/upload"})
    public String upload(@RequestParam("examen") MultipartFile examen, HttpSession session) throws IOException, IOException {
        Consulta consultaT = (Consulta)session.getAttribute("consultaT");
        if (consultaT == null) {
            new Consulta();
        }

        Consulta consulta = new Consulta();
        consulta.setExamen(examen.getBytes());
        session.setAttribute("consultaT", consulta);
        System.out.println(Arrays.toString(consulta.getExamen()));
        return "historia_clinica";
    }

    @PostMapping({"/uploadd"})
    public String uploadd(@RequestParam("examen") MultipartFile examen, HttpSession session) throws IOException {
        Consulta consultaT = (Consulta)session.getAttribute("consultaT");
        if (consultaT == null) {
            consultaT = new Consulta();
        }

        if (examen != null && !examen.isEmpty()) {
            byte[] contenidoExamen = examen.getBytes();
            consultaT.setExamen(contenidoExamen);
        }

        session.setAttribute("consultaT", consultaT);
        System.out.println(Arrays.toString(consultaT.getExamen()));
        return "historia_clinica";
    }

    @PostMapping({"/save"})
    public String saveConsulta(@ModelAttribute("consulta") Consulta consulta, RedirectAttributesModelMap modelMap, RedirectAttributes attributes, @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            consulta.setExamen(file.getBytes());
        }

        modelMap.addFlashAttribute("miVariable", this.id);
        attributes.addAttribute("pacienteId", this.id);
        Paciente paciente = (Paciente)this.pacienteService.findById(this.id).orElse((Paciente) null);
        consulta.setPaciente(paciente);
        this.consultaRepository.save(consulta);
        return "redirect:/api/historias_clinicas/nuevo";
    }

    @GetMapping({"/examen/{id}"})
    public void view(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Optional<Consulta> consulta = this.consultaRepository.findById(id);
        if (consulta.isPresent()) {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=examen.pdf");
            response.getOutputStream().write(((Consulta)consulta.get()).getExamen());
        }

    }




    //Editar Diagnóstico, Tratamiento y Comentario
    @PostMapping("/actualizarConsulta")
    public String actualizarConsulta(@RequestParam("consultaIdEdit") Long consultaIdEdit,
                                     @RequestParam("diagnostico") String diagnostico,
                                     @RequestParam("tratamiento") String tratamiento,
                                     @RequestParam("comentarioedit") String comentarioedit,
                                     HttpSession session)
    {
        Consulta consulta = consultaRepository.findById(consultaIdEdit)
                .orElseThrow(() -> new IllegalArgumentException("Consulta no encontrada con ID: " + consultaIdEdit));

        // Actualiza los campos en la consulta
        consulta.setDiagnostico(diagnostico);
        consulta.setTratamiento(tratamiento);
        consulta.setComentario(comentarioedit);

        // Guarda la consulta actualizada
        consultaRepository.save(consulta);

        // Redirige a la página de la historia clínica
        return "redirect:/api/historias_clinicas/nuevo?pacienteId=" + this.id;
    }






}
