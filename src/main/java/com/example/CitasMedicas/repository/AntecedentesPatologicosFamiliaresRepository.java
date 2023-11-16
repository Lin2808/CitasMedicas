package com.example.CitasMedicas.repository;

import com.example.CitasMedicas.model.AntecedentesPatologicosFamiliares;
import com.example.CitasMedicas.model.Paciente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AntecedentesPatologicosFamiliaresRepository extends JpaRepository<AntecedentesPatologicosFamiliares, Long>{
    List<AntecedentesPatologicosFamiliares> findByPaciente(Paciente paciente);
    List<AntecedentesPatologicosFamiliares> findByPaciente_Id(Long id);
}
