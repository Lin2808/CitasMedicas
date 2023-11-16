package com.example.CitasMedicas.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@Entity
public class HistoriaClinica {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private LocalDateTime fechRegistro;

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

    public void setId(final Long id) {
        this.id = id;
    }

    public void setFechRegistro(final LocalDateTime fechRegistro) {
        this.fechRegistro = fechRegistro;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof HistoriaClinica)) {
            return false;
        } else {
            HistoriaClinica other = (HistoriaClinica)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
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

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof HistoriaClinica;
    }

    public int hashCode() {
        //int PRIME = true;
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $fechRegistro = this.getFechRegistro();
        result = result * 59 + ($fechRegistro == null ? 43 : $fechRegistro.hashCode());
        return result;
    }

    public String toString() {
        Long var10000 = this.getId();
        return "HistoriaClinica(id=" + var10000 + ", fechRegistro=" + this.getFechRegistro() + ")";
    }

    public HistoriaClinica() {
    }
}
