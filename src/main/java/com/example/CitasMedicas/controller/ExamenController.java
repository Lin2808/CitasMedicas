package com.example.CitasMedicas.controller;

import com.example.CitasMedicas.model.Examen;
import com.example.CitasMedicas.service.ExamenService;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping({"/examenes"})
public class ExamenController {
    private static final Logger logger = LoggerFactory.getLogger(ExamenController.class);
    @Autowired
    private ExamenService examenService;

    public ExamenController() {
    }

    @GetMapping({"/subir"})
    public String mostrarFormularioSubirExamen(Model model) {
        model.addAttribute("examen", new Examen());
        return "formulario-subir-examen";
    }

    @PostMapping({"/subir"})
    public String subirExamen(@ModelAttribute Examen examen, @RequestParam("archivoPDF") MultipartFile archivoPDF) {
        try {
            this.examenService.guardarPDF(examen, archivoPDF);
            logger.info("PDF guardado con éxito");
            return "redirect:/index";
        } catch (IOException var4) {
            logger.error("Error al guardar el PDF", var4);
            return "redirect:/ruta-de-error";
        }
    }
}
