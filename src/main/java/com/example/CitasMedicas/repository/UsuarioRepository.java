package com.example.CitasMedicas.repository;

import com.example.CitasMedicas.model.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByUsername(String username);

    @Query("SELECT u FROM Usuario u WHERE u.persona.nombres LIKE %:nombre% OR u.persona.apellidos LIKE %:apellido% OR u.persona.cedula LIKE %:cedula%")
    List<Usuario> buscarPorNombreOApellidoCedula(@Param("nombre") String nombre, @Param("apellido") String apellido, @Param("cedula") String cedula);
}
