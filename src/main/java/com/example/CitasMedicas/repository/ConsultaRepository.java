package com.example.CitasMedicas.repository;

import com.example.CitasMedicas.model.Consulta;
import com.example.CitasMedicas.model.Paciente;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    //List<Consulta> findByPaciente_Id(long id);

    //List<Consulta> findByPaciente(Paciente paciente);

    List<Consulta> findByPaciente_Id(long id, Sort sort);

    List<Consulta> findByPaciente(Paciente paciente);

}
