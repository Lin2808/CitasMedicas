package com.example.CitasMedicas.repository;

import com.example.CitasMedicas.model.Examen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamenRepository extends JpaRepository<Examen, Long>{
}
