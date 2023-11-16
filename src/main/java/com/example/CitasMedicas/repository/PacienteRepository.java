package com.example.CitasMedicas.repository;

import com.example.CitasMedicas.model.Paciente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{

    @Query("SELECT p FROM Paciente p WHERE p.persona.nombres LIKE %:nombre% OR p.persona.apellidos LIKE %:apellido% OR p.persona.cedula LIKE %:cedula%")
    List<Paciente> buscarPorNombreOApellido(@Param("nombre") String nombre, @Param("apellido") String apellido, @Param("cedula") String cedula);
}
