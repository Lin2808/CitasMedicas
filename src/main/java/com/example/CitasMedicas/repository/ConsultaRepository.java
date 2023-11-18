package com.example.CitasMedicas.repository;

import com.example.CitasMedicas.model.Consulta;
import com.example.CitasMedicas.model.Paciente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByPaciente_Id(long id);

    List<Consulta> findByPaciente(Paciente paciente);

    // Método personalizado para obtener todas las consultas ordenadas por fechRegistro
    List<Consulta> findAllByOrderByFechRegistroDesc();

    // Método personalizado para obtener consultas por paciente ordenadas por fechRegistro
    List<Consulta> findByPacienteOrderByFechRegistroDesc(Paciente paciente);

}
