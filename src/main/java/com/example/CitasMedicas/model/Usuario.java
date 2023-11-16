package com.example.CitasMedicas.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String username;
    private String password;
    private boolean enabled;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    @ManyToOne
    @JoinColumn(
            name = "person_id"
    )
    private Persona persona;

    public Long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public Rol getRol() {
        return this.rol;
    }

    public Persona getPersona() {
        return this.persona;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    public void setRol(final Rol rol) {
        this.rol = rol;
    }

    public void setPersona(final Persona persona) {
        this.persona = persona;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Usuario)) {
            return false;
        } else {
            Usuario other = (Usuario)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.isEnabled() != other.isEnabled()) {
                return false;
            } else {
                label73: {
                    Object this$id = this.getId();
                    Object other$id = other.getId();
                    if (this$id == null) {
                        if (other$id == null) {
                            break label73;
                        }
                    } else if (this$id.equals(other$id)) {
                        break label73;
                    }

                    return false;
                }

                Object this$username = this.getUsername();
                Object other$username = other.getUsername();
                if (this$username == null) {
                    if (other$username != null) {
                        return false;
                    }
                } else if (!this$username.equals(other$username)) {
                    return false;
                }

                label59: {
                    Object this$password = this.getPassword();
                    Object other$password = other.getPassword();
                    if (this$password == null) {
                        if (other$password == null) {
                            break label59;
                        }
                    } else if (this$password.equals(other$password)) {
                        break label59;
                    }

                    return false;
                }

                Object this$rol = this.getRol();
                Object other$rol = other.getRol();
                if (this$rol == null) {
                    if (other$rol != null) {
                        return false;
                    }
                } else if (!this$rol.equals(other$rol)) {
                    return false;
                }

                Object this$persona = this.getPersona();
                Object other$persona = other.getPersona();
                if (this$persona == null) {
                    if (other$persona != null) {
                        return false;
                    }
                } else if (!this$persona.equals(other$persona)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Usuario;
    }

    public int hashCode() {
        //int PRIME = true;
        int result = 1;
        result = result * 59 + (this.isEnabled() ? 79 : 97);
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $username = this.getUsername();
        result = result * 59 + ($username == null ? 43 : $username.hashCode());
        Object $password = this.getPassword();
        result = result * 59 + ($password == null ? 43 : $password.hashCode());
        Object $rol = this.getRol();
        result = result * 59 + ($rol == null ? 43 : $rol.hashCode());
        Object $persona = this.getPersona();
        result = result * 59 + ($persona == null ? 43 : $persona.hashCode());
        return result;
    }

    public String toString() {
        Long var10000 = this.getId();
        return "Usuario(id=" + var10000 + ", username=" + this.getUsername() + ", password=" + this.getPassword() + ", enabled=" + this.isEnabled() + ", rol=" + this.getRol() + ", persona=" + this.getPersona() + ")";
    }

    public Usuario() {
    }
}
