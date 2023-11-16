package com.example.CitasMedicas.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

@Entity
public class Paciente {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String antecedentesPersonales;
    private LocalDateTime fechaRegistro;
    private boolean estado;
    @OneToOne
    private Persona persona;

    @PrePersist
    public void prePersist() {
        this.fechaRegistro = LocalDateTime.now();
    }

    public Long getId() {
        return this.id;
    }

    public String getAntecedentesPersonales() {
        return this.antecedentesPersonales;
    }

    public LocalDateTime getFechaRegistro() {
        return this.fechaRegistro;
    }

    public boolean isEstado() {
        return this.estado;
    }

    public Persona getPersona() {
        return this.persona;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setAntecedentesPersonales(final String antecedentesPersonales) {
        this.antecedentesPersonales = antecedentesPersonales;
    }

    public void setFechaRegistro(final LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public void setEstado(final boolean estado) {
        this.estado = estado;
    }

    public void setPersona(final Persona persona) {
        this.persona = persona;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Paciente)) {
            return false;
        } else {
            Paciente other = (Paciente)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.isEstado() != other.isEstado()) {
                return false;
            } else {
                label61: {
                    Object this$id = this.getId();
                    Object other$id = other.getId();
                    if (this$id == null) {
                        if (other$id == null) {
                            break label61;
                        }
                    } else if (this$id.equals(other$id)) {
                        break label61;
                    }

                    return false;
                }

                label54: {
                    Object this$antecedentesPersonales = this.getAntecedentesPersonales();
                    Object other$antecedentesPersonales = other.getAntecedentesPersonales();
                    if (this$antecedentesPersonales == null) {
                        if (other$antecedentesPersonales == null) {
                            break label54;
                        }
                    } else if (this$antecedentesPersonales.equals(other$antecedentesPersonales)) {
                        break label54;
                    }

                    return false;
                }

                Object this$fechaRegistro = this.getFechaRegistro();
                Object other$fechaRegistro = other.getFechaRegistro();
                if (this$fechaRegistro == null) {
                    if (other$fechaRegistro != null) {
                        return false;
                    }
                } else if (!this$fechaRegistro.equals(other$fechaRegistro)) {
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
        return other instanceof Paciente;
    }

    public int hashCode() {
        //int PRIME = true;
        int result = 1;
        result = result * 59 + (this.isEstado() ? 79 : 97);
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $antecedentesPersonales = this.getAntecedentesPersonales();
        result = result * 59 + ($antecedentesPersonales == null ? 43 : $antecedentesPersonales.hashCode());
        Object $fechaRegistro = this.getFechaRegistro();
        result = result * 59 + ($fechaRegistro == null ? 43 : $fechaRegistro.hashCode());
        Object $persona = this.getPersona();
        result = result * 59 + ($persona == null ? 43 : $persona.hashCode());
        return result;
    }

    public String toString() {
        Long var10000 = this.getId();
        return "Paciente(id=" + var10000 + ", antecedentesPersonales=" + this.getAntecedentesPersonales() + ", fechaRegistro=" + this.getFechaRegistro() + ", estado=" + this.isEstado() + ", persona=" + this.getPersona() + ")";
    }

    public Paciente() {
    }
}
