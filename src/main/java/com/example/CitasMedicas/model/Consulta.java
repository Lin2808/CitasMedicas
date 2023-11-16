package com.example.CitasMedicas.model;

import java.time.LocalDateTime;
import java.util.Arrays;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

@Entity
public class Consulta {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private LocalDateTime fechRegistro;
    private String peso;
    private String temperatura;
    private String talla;
    private String presion;
    private String diagnostico;
    private String tratamiento;
    private String alergias;
    private String comentario;
    @Column(
            name = "examen",
            columnDefinition = "BYTEA"
    )
    private byte[] examen;
    @ManyToOne
    @JoinColumn(
            name = "paciente_id"
    )
    private Paciente paciente;

    @PrePersist
    public void prePersist() {
        this.fechRegistro = LocalDateTime.now();
    }

    public Long getId() {
        return this.id;
    }

    public LocalDateTime getFechRegistro() {
        return this.fechRegistro;
    }

    public String getPeso() {
        return this.peso;
    }

    public String getTemperatura() {
        return this.temperatura;
    }

    public String getTalla() {
        return this.talla;
    }

    public String getPresion() {
        return this.presion;
    }

    public String getDiagnostico() {
        return this.diagnostico;
    }

    public String getTratamiento() {
        return this.tratamiento;
    }

    public String getAlergias() {
        return this.alergias;
    }

    public String getComentario() {
        return this.comentario;
    }

    public byte[] getExamen() {
        return this.examen;
    }

    public Paciente getPaciente() {
        return this.paciente;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setFechRegistro(final LocalDateTime fechRegistro) {
        this.fechRegistro = fechRegistro;
    }

    public void setPeso(final String peso) {
        this.peso = peso;
    }

    public void setTemperatura(final String temperatura) {
        this.temperatura = temperatura;
    }

    public void setTalla(final String talla) {
        this.talla = talla;
    }

    public void setPresion(final String presion) {
        this.presion = presion;
    }

    public void setDiagnostico(final String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public void setTratamiento(final String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public void setAlergias(final String alergias) {
        this.alergias = alergias;
    }

    public void setComentario(final String comentario) {
        this.comentario = comentario;
    }

    public void setExamen(final byte[] examen) {
        this.examen = examen;
    }

    public void setPaciente(final Paciente paciente) {
        this.paciente = paciente;
    }

    public boolean equals(final Object o)
    {
        if (o == this)
        {
            return true;
        }
        else
            if (!(o instanceof Consulta))
            {
                return false;
            }
            else
            {
            Consulta other = (Consulta)o;
            if (!other.canEqual(this))
            {
                return false;
            }
            else
            {
                Object this$id = this.getId();
                Object other$id = other.getId();
                if (this$id == null) {
                    if (other$id != null) {
                        return false;
                    }
                } else if (!this$id.equals(other$id)) {
                    return false;
                }

                Object this$fechRegistro = this.getFechRegistro();
                Object other$fechRegistro = other.getFechRegistro();
                if (this$fechRegistro == null) {
                    if (other$fechRegistro != null) {
                        return false;
                    }
                } else if (!this$fechRegistro.equals(other$fechRegistro)) {
                    return false;
                }

                Object this$peso = this.getPeso();
                Object other$peso = other.getPeso();
                if (this$peso == null) {
                    if (other$peso != null) {
                        return false;
                    }
                } else if (!this$peso.equals(other$peso)) {
                    return false;
                }

                label126: {
                    Object this$temperatura = this.getTemperatura();
                    Object other$temperatura = other.getTemperatura();
                    if (this$temperatura == null) {
                        if (other$temperatura == null) {
                            break label126;
                        }
                    } else if (this$temperatura.equals(other$temperatura)) {
                        break label126;
                    }

                    return false;
                }

                label119: {
                    Object this$talla = this.getTalla();
                    Object other$talla = other.getTalla();
                    if (this$talla == null) {
                        if (other$talla == null) {
                            break label119;
                        }
                    } else if (this$talla.equals(other$talla)) {
                        break label119;
                    }

                    return false;
                }

                Object this$presion = this.getPresion();
                Object other$presion = other.getPresion();
                if (this$presion == null) {
                    if (other$presion != null) {
                        return false;
                    }
                } else if (!this$presion.equals(other$presion)) {
                    return false;
                }

                label105: {
                    Object this$diagnostico = this.getDiagnostico();
                    Object other$diagnostico = other.getDiagnostico();
                    if (this$diagnostico == null) {
                        if (other$diagnostico == null) {
                            break label105;
                        }
                    } else if (this$diagnostico.equals(other$diagnostico)) {
                        break label105;
                    }

                    return false;
                }

                label98: {
                    Object this$tratamiento = this.getTratamiento();
                    Object other$tratamiento = other.getTratamiento();
                    if (this$tratamiento == null) {
                        if (other$tratamiento == null) {
                            break label98;
                        }
                    } else if (this$tratamiento.equals(other$tratamiento)) {
                        break label98;
                    }

                    return false;
                }

                Object this$alergias = this.getAlergias();
                Object other$alergias = other.getAlergias();
                if (this$alergias == null) {
                    if (other$alergias != null) {
                        return false;
                    }
                } else if (!this$alergias.equals(other$alergias)) {
                    return false;
                }

                Object this$comentario = this.getComentario();
                Object other$comentario = other.getComentario();
                if (this$comentario == null) {
                    if (other$comentario != null) {
                        return false;
                    }
                } else if (!this$comentario.equals(other$comentario)) {
                    return false;
                }

                if (!Arrays.equals(this.getExamen(), other.getExamen())) {
                    return false;
                } else {
                    Object this$paciente = this.getPaciente();
                    Object other$paciente = other.getPaciente();
                    if (this$paciente == null) {
                        if (other$paciente == null) {
                            return true;
                        }
                    } else if (this$paciente.equals(other$paciente)) {
                        return true;
                    }

                    return false;
                }
            }
        }
    }

    protected boolean canEqual(final Object other)
    {
        return other instanceof Consulta;
    }

    public int hashCode()
    {
        //int PRIME = true;
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $fechRegistro = this.getFechRegistro();
        result = result * 59 + ($fechRegistro == null ? 43 : $fechRegistro.hashCode());
        Object $peso = this.getPeso();
        result = result * 59 + ($peso == null ? 43 : $peso.hashCode());
        Object $temperatura = this.getTemperatura();
        result = result * 59 + ($temperatura == null ? 43 : $temperatura.hashCode());
        Object $talla = this.getTalla();
        result = result * 59 + ($talla == null ? 43 : $talla.hashCode());
        Object $presion = this.getPresion();
        result = result * 59 + ($presion == null ? 43 : $presion.hashCode());
        Object $diagnostico = this.getDiagnostico();
        result = result * 59 + ($diagnostico == null ? 43 : $diagnostico.hashCode());
        Object $tratamiento = this.getTratamiento();
        result = result * 59 + ($tratamiento == null ? 43 : $tratamiento.hashCode());
        Object $alergias = this.getAlergias();
        result = result * 59 + ($alergias == null ? 43 : $alergias.hashCode());
        Object $comentario = this.getComentario();
        result = result * 59 + ($comentario == null ? 43 : $comentario.hashCode());
        result = result * 59 + Arrays.hashCode(this.getExamen());
        Object $paciente = this.getPaciente();
        result = result * 59 + ($paciente == null ? 43 : $paciente.hashCode());
        return result;
    }

    public String toString()
    {
        Long var10000 = this.getId();
        return "Consulta(id=" + var10000 + ", fechRegistro=" + this.getFechRegistro() + ", peso=" + this.getPeso() + ", temperatura=" + this.getTemperatura() + ", talla=" + this.getTalla() + ", presion=" + this.getPresion() + ", diagnostico=" + this.getDiagnostico() + ", tratamiento=" + this.getTratamiento() + ", alergias=" + this.getAlergias() + ", comentario=" + this.getComentario() + ", examen=" + Arrays.toString(this.getExamen()) + ", paciente=" + this.getPaciente() + ")";
    }

    public Consulta()
    {

    }
}
