package com.example.CitasMedicas.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Persona {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String apellidos;
    private String nombres;
    private String cedula;
    private String direccion;
    private String sexo;
    private String telefono;
    @DateTimeFormat(
            pattern = "yy-MM-dd"
    )
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    public Long getId() {
        return this.id;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public String getNombres() {
        return this.nombres;
    }

    public String getCedula() {
        return this.cedula;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public String getSexo() {
        return this.sexo;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setApellidos(final String apellidos) {
        this.apellidos = apellidos;
    }

    public void setNombres(final String nombres) {
        this.nombres = nombres;
    }

    public void setCedula(final String cedula) {
        this.cedula = cedula;
    }

    public void setDireccion(final String direccion) {
        this.direccion = direccion;
    }

    public void setSexo(final String sexo) {
        this.sexo = sexo;
    }

    public void setTelefono(final String telefono) {
        this.telefono = telefono;
    }

    public void setFechaNacimiento(final Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Persona)) {
            return false;
        } else {
            Persona other = (Persona)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label107: {
                    Object this$id = this.getId();
                    Object other$id = other.getId();
                    if (this$id == null) {
                        if (other$id == null) {
                            break label107;
                        }
                    } else if (this$id.equals(other$id)) {
                        break label107;
                    }

                    return false;
                }

                Object this$apellidos = this.getApellidos();
                Object other$apellidos = other.getApellidos();
                if (this$apellidos == null) {
                    if (other$apellidos != null) {
                        return false;
                    }
                } else if (!this$apellidos.equals(other$apellidos)) {
                    return false;
                }

                Object this$nombres = this.getNombres();
                Object other$nombres = other.getNombres();
                if (this$nombres == null) {
                    if (other$nombres != null) {
                        return false;
                    }
                } else if (!this$nombres.equals(other$nombres)) {
                    return false;
                }

                label86: {
                    Object this$cedula = this.getCedula();
                    Object other$cedula = other.getCedula();
                    if (this$cedula == null) {
                        if (other$cedula == null) {
                            break label86;
                        }
                    } else if (this$cedula.equals(other$cedula)) {
                        break label86;
                    }

                    return false;
                }

                label79: {
                    Object this$direccion = this.getDireccion();
                    Object other$direccion = other.getDireccion();
                    if (this$direccion == null) {
                        if (other$direccion == null) {
                            break label79;
                        }
                    } else if (this$direccion.equals(other$direccion)) {
                        break label79;
                    }

                    return false;
                }

                label72: {
                    Object this$sexo = this.getSexo();
                    Object other$sexo = other.getSexo();
                    if (this$sexo == null) {
                        if (other$sexo == null) {
                            break label72;
                        }
                    } else if (this$sexo.equals(other$sexo)) {
                        break label72;
                    }

                    return false;
                }

                Object this$telefono = this.getTelefono();
                Object other$telefono = other.getTelefono();
                if (this$telefono == null) {
                    if (other$telefono != null) {
                        return false;
                    }
                } else if (!this$telefono.equals(other$telefono)) {
                    return false;
                }

                Object this$fechaNacimiento = this.getFechaNacimiento();
                Object other$fechaNacimiento = other.getFechaNacimiento();
                if (this$fechaNacimiento == null) {
                    if (other$fechaNacimiento != null) {
                        return false;
                    }
                } else if (!this$fechaNacimiento.equals(other$fechaNacimiento)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Persona;
    }

    public int hashCode() {
        //int PRIME = true;
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $apellidos = this.getApellidos();
        result = result * 59 + ($apellidos == null ? 43 : $apellidos.hashCode());
        Object $nombres = this.getNombres();
        result = result * 59 + ($nombres == null ? 43 : $nombres.hashCode());
        Object $cedula = this.getCedula();
        result = result * 59 + ($cedula == null ? 43 : $cedula.hashCode());
        Object $direccion = this.getDireccion();
        result = result * 59 + ($direccion == null ? 43 : $direccion.hashCode());
        Object $sexo = this.getSexo();
        result = result * 59 + ($sexo == null ? 43 : $sexo.hashCode());
        Object $telefono = this.getTelefono();
        result = result * 59 + ($telefono == null ? 43 : $telefono.hashCode());
        Object $fechaNacimiento = this.getFechaNacimiento();
        result = result * 59 + ($fechaNacimiento == null ? 43 : $fechaNacimiento.hashCode());
        return result;
    }

    public String toString() {
        Long var10000 = this.getId();
        return "Persona(id=" + var10000 + ", apellidos=" + this.getApellidos() + ", nombres=" + this.getNombres() + ", cedula=" + this.getCedula() + ", direccion=" + this.getDireccion() + ", sexo=" + this.getSexo() + ", telefono=" + this.getTelefono() + ", fechaNacimiento=" + this.getFechaNacimiento() + ")";
    }

    public Persona() {
    }
}
