package com.example.CitasMedicas.repository;

import com.example.CitasMedicas.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    boolean existsByCedula(String cedula);
}
