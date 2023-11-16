package com.example.CitasMedicas.service;

import com.example.CitasMedicas.model.Paciente;
import com.example.CitasMedicas.model.Persona;
import com.example.CitasMedicas.repository.PacienteRepository;
import com.example.CitasMedicas.repository.PersonaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    PacienteRepository pacienteRepository;

    public PacienteService() {
    }

    @Transactional
    public void save(Paciente paciente)
    {
        Persona persona = paciente.getPersona();
        Persona temp = (Persona)this.personaRepository.save(persona);
        paciente.setPersona(temp);
        this.pacienteRepository.save(paciente);
    }

    public List<Paciente> listar()
    {
        return this.pacienteRepository.findAll();
    }

    public List<Paciente> buscarPorNombresOApellidos(String nombre, String apellido, String cedula)
    {
        return this.pacienteRepository.buscarPorNombreOApellido(nombre, apellido, cedula);
    }

    public Optional<Paciente> findById(Long id)
    {
        return this.pacienteRepository.findById(id);
    }
}
