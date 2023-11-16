package com.example.CitasMedicas.security.service;

import com.example.CitasMedicas.model.Usuario;
import com.example.CitasMedicas.repository.UsuarioRepository;
import java.util.Collections;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("usuarioSecurityService")
public class UsuarioService implements UserDetailsService{
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioService() {
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> optionalUsuario = this.usuarioRepository.findByUsername(username);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = (Usuario)optionalUsuario.get();
            return new User(usuario.getUsername(), usuario.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(usuario.getRol().name())));
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }
}
