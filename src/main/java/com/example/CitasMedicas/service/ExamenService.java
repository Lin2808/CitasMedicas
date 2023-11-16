package com.example.CitasMedicas.service;

import com.example.CitasMedicas.model.Examen;
import com.example.CitasMedicas.repository.ExamenRepository;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ExamenService {
    @Autowired
    private ExamenRepository examenRepository;

    public ExamenService() {
    }

    public void guardarPDF(Examen examen, MultipartFile archivoPDF) throws IOException
    {
        if (archivoPDF != null && archivoPDF.getContentType().equals("application/pdf"))
        {
            byte[] archivoBytes = archivoPDF.getBytes();
            examen.setArchivo(archivoBytes);
            this.examenRepository.save(examen);
        }
        else
        {
            throw new IllegalArgumentException("El archivo no es un PDF válido.");
        }
    }
}
