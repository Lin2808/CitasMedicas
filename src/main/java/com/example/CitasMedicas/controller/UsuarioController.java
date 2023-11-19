package com.example.CitasMedicas.controller;

import com.example.CitasMedicas.model.Persona;
import com.example.CitasMedicas.model.Rol;
import com.example.CitasMedicas.model.Usuario;
import com.example.CitasMedicas.repository.PersonaRepository;
import com.example.CitasMedicas.repository.UsuarioRepository;
import com.example.CitasMedicas.service.PacienteService;
import com.example.CitasMedicas.service.UsuarioServiceI;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping({"/api/usuarios"})
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PacienteService usuarioService;
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    UsuarioServiceI usuarioServiceI;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioController() {
    }

    @GetMapping
    public String listar(Model model) {
        List<Usuario> usuarios = this.usuarioRepository.findAll();
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("usuarios", usuarios);
        return "usuarios";
    }

    @GetMapping({"/tu-pagina"})
    public String tuPagina(Authentication authentication, Model model) {
        if (authentication != null) {
            UserDetails userDetails = (UserDetails)authentication.getPrincipal();
            String username = userDetails.getUsername();
            Optional<Usuario> usuario = this.usuarioRepository.findByUsername(username);
            model.addAttribute("usuario", usuario.get());
            model.addAttribute("username", username);
        }

        return "tu-pagina";
    }



    @GetMapping({"/cambio_password"})
    public String cambioPassword() {
        return "cambiar_contrasenia";
    }

    @PostMapping({"/save"})
    public String save(@ModelAttribute("usuarioT") Usuario usuario, RedirectAttributes attributes) {
        if (!this.usuarioServiceI.existeUsername(usuario.getUsername())) {
            Persona persona = new Persona();
            persona.setApellidos(usuario.getPersona().getApellidos());
            persona.setNombres(usuario.getPersona().getNombres());
            persona.setDireccion(usuario.getPersona().getDireccion());
            persona.setSexo(usuario.getPersona().getSexo());
            persona.setTelefono(usuario.getPersona().getTelefono());
            persona.setFechaNacimiento(usuario.getPersona().getFechaNacimiento());
            this.personaRepository.save(persona);
            usuario.setPersona(persona);
            String pasEncode = this.passwordEncoder.encode(usuario.getPassword());
            usuario.setPassword(pasEncode);
            usuario.setEnabled(true);
            String rol = String.valueOf(usuario.getRol());
            if ("ROLE_USER".equals(rol)) {
                usuario.setRol(Rol.ROLE_USER);
            } else {
                usuario.setRol(Rol.ROLE_ADMIN);
            }

            this.usuarioRepository.save(usuario);
            attributes.addFlashAttribute("mensaje", "El usuario se ha registrado correctamente");
        } else {
            attributes.addFlashAttribute("error", "El nombre de usuario ya existe en la base de datos, pruebe con otro");
        }

        return "redirect:/api/usuarios";
    }

    @PostMapping({"/buscar"})
    public String buscarPorNombreOrApellido(@RequestParam("nombreApellido") String nombreApellido, Model model) {
        List<Usuario> usuarios = this.usuarioRepository.buscarPorNombreOApellidoCedula(nombreApellido, nombreApellido, nombreApellido);
        model.addAttribute("usuarios", usuarios);
        return "usuarios";
    }

    @GetMapping({"/editar/{id}"})
    public String editar(@PathVariable Long id, Model model) {
        Optional<Usuario> usuario = this.usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            model.addAttribute("usuario", usuario.get());
            System.out.println(usuario);
            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        } else {
            model.addAttribute("usuario", (Object)null);
        }

        return "editar_usuario";
    }

    @PostMapping({"/update"})
    public String updateUsuario(@ModelAttribute("usuario") Usuario usuario, RedirectAttributes attributes) {
        Optional<Usuario> optionalUsuarioExistente = this.usuarioRepository.findById(usuario.getId());
        if (optionalUsuarioExistente.isPresent()) {
            Usuario usuarioExistente = (Usuario)optionalUsuarioExistente.get();
            Persona personaExistente = usuarioExistente.getPersona();
            if (!usuario.getUsername().equals(usuarioExistente.getUsername())) {
                if (this.usuarioServiceI.existeUsername(usuario.getUsername())) {
                    attributes.addFlashAttribute("error", "El nombre de usuario ya existe en la base de datos, pruebe con otro");
                    return "redirect:/api/usuarios";
                }

                usuarioExistente.setUsername(usuario.getUsername());
            }

            personaExistente.setNombres(usuario.getPersona().getNombres());
            personaExistente.setApellidos(usuario.getPersona().getApellidos());
            personaExistente.setSexo(usuario.getPersona().getSexo());
            personaExistente.setFechaNacimiento(usuario.getPersona().getFechaNacimiento());
            personaExistente.setDireccion(usuario.getPersona().getDireccion());
            personaExistente.setTelefono(usuario.getPersona().getTelefono());
            usuarioExistente.setRol(usuario.getRol());
            usuarioExistente.setPersona(personaExistente);
            this.usuarioRepository.save(usuarioExistente);
            attributes.addFlashAttribute("mensaje", "El usuario se ha actualizado correctamente");
        } else {
            attributes.addFlashAttribute("error", "El usuario no existe en la base de datos");
        }

        return "redirect:/api/usuarios";
    }

    @PostMapping({"/updatee"})
    public String otros(@ModelAttribute("usuario") Usuario usuario, RedirectAttributes attributes) {
        Usuario usuarioExistente = (Usuario)this.usuarioRepository.findById(usuario.getId()).get();
        Persona personaExistente = usuarioExistente.getPersona();
        String us = usuarioExistente.getUsername();
        usuarioExistente.setUsername(usuario.getUsername());
        personaExistente.setNombres(usuario.getPersona().getNombres());
        personaExistente.setApellidos(usuario.getPersona().getApellidos());
        personaExistente.setCedula(usuario.getPersona().getCedula());
        personaExistente.setFechaNacimiento(usuario.getPersona().getFechaNacimiento());
        personaExistente.setDireccion(usuario.getPersona().getDireccion());
        usuarioExistente.setPersona(personaExistente);
        this.usuarioRepository.save(usuarioExistente);
        if (us.equals(usuario.getUsername())) {
            System.out.println("base de datos");
            System.out.println(usuarioExistente.getUsername());
            System.out.println("Formulario");
            System.out.println(usuario.getUsername());
            attributes.addFlashAttribute("mensaje", "El usuario se ha actualizado correctamente");
            return "redirect:/api/usuarios/perfil";
        } else {
            return "redirect:/logout";
        }
    }

    @GetMapping({"/eliminar/{id}"})
    public String eliminarUsuario(@PathVariable Long id, Model model) {
        model.addAttribute("usuario", this.usuarioRepository.findById(id).get());
        return "eliminar_usuario";
    }

    @GetMapping({"/eliminarUsuario/{id}"})
    public String eliminarUsuario(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Usuario> usuarioOptional = this.usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            this.usuarioRepository.deleteById(id);

            // Eliminar la entrada correspondiente en la tabla Persona
            Persona persona = usuario.getPersona();
            if (persona != null) {
                this.personaRepository.delete(persona);
            }

            redirectAttributes.addFlashAttribute("mensaje", "El usuario se ha eliminado correctamente");
        } else {
            redirectAttributes.addFlashAttribute("error", "Usuario no encontrado.");
        }

        return "redirect:/api/usuarios";
    }


    @GetMapping({"/cambiar-password"})
    public String mostrarFormularioCambioContrasena() {
        return "cambiar_contrasenia";
    }

    @PostMapping({"/cambiar-password"})
    public String cambiarContrasenad(@RequestParam("currentPassword") String currentPassword, @RequestParam("newPassword") String newPassword, Model model) {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        Usuario usuario = (Usuario)this.usuarioRepository.findByUsername(username).get();
        if (this.passwordEncoder.matches(currentPassword, usuario.getPassword())) {
            if (!this.passwordEncoder.matches(newPassword, usuario.getPassword())) {
                String hashedNewPassword = this.passwordEncoder.encode(newPassword);
                usuario.setPassword(hashedNewPassword);
                this.usuarioRepository.save(usuario);
                model.addAttribute("successMessage", "Contraseña cambiada con éxito");
                return "cambiar_contrasenia";
            }

            model.addAttribute("errorMessage", "La nueva contraseña no puede ser igual a la actual");
        } else {
            model.addAttribute("errorMessage", "Contraseña actual incorrecta");
        }

        return "cambiar_contrasenia";
    }
}
