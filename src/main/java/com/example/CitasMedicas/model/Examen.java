package com.example.CitasMedicas.model;

import java.util.Arrays;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Examen {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Lob
    private byte[] archivo;

    public Long getId() {
        return this.id;
    }

    public byte[] getArchivo() {
        return this.archivo;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setArchivo(final byte[] archivo) {
        this.archivo = archivo;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Examen)) {
            return false;
        } else {
            Examen other = (Examen)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$id = this.getId();
                Object other$id = other.getId();
                if (this$id == null) {
                    if (other$id == null) {
                        return Arrays.equals(this.getArchivo(), other.getArchivo());
                    }
                } else if (this$id.equals(other$id)) {
                    return Arrays.equals(this.getArchivo(), other.getArchivo());
                }

                return false;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Examen;
    }

    public int hashCode() {
        //int PRIME = true;
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        result = result * 59 + Arrays.hashCode(this.getArchivo());
        return result;
    }

    public String toString() {
        Long var10000 = this.getId();
        return "Examen(id=" + var10000 + ", archivo=" + Arrays.toString(this.getArchivo()) + ")";
    }

    public Examen() {
    }
}
