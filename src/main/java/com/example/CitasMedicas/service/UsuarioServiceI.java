package com.example.CitasMedicas.service;

import com.example.CitasMedicas.model.Usuario;
import com.example.CitasMedicas.repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceI {
    @Autowired
    UsuarioRepository usuarioRepository;

    public UsuarioServiceI() {
    }

    public boolean existeUsuarioPorNombre(String nombreUsuario) {
        Optional<Usuario> usuarioOptional = this.usuarioRepository.findByUsername(nombreUsuario);
        return usuarioOptional.isPresent();
    }

    public boolean existeUsername(String nombreUsuario) {
        Optional<Usuario> usuarioOptional = this.usuarioRepository.findByUsername(nombreUsuario);
        return usuarioOptional.isPresent();
    }
}
