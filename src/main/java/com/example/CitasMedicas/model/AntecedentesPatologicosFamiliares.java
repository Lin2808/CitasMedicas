package com.example.CitasMedicas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AntecedentesPatologicosFamiliares {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String patologia;
    private String alergias;
    @ManyToOne
    @JoinColumn(
            name = "paciente_id"
    )
    private Paciente paciente;

    public Long getId() {
        return this.id;
    }

    public String getPatologia() {
        return this.patologia;
    }

    public String getAlergias() {
        return this.alergias;
    }

    public Paciente getPaciente() {
        return this.paciente;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setPatologia(final String patologia) {
        this.patologia = patologia;
    }

    public void setAlergias(final String alergias) {
        this.alergias = alergias;
    }

    public void setPaciente(final Paciente paciente) {
        this.paciente = paciente;
    }

    public boolean equals(final Object o) {
        if (o == this)
        {
            return true;
        }
        else
        {
            if (!(o instanceof AntecedentesPatologicosFamiliares))
            {
                return false;
            }
            else
            {
                AntecedentesPatologicosFamiliares other = (AntecedentesPatologicosFamiliares) o;
                if (!other.canEqual(this))
                {
                    return false;
                }
                else
                {
                    label59:
                    {
                        Object this$id = this.getId();
                        Object other$id = other.getId();
                        if (this$id == null)
                        {
                            if (other$id == null)
                            {
                                break label59;
                            }
                        }
                        else
                        {
                            if (this$id.equals(other$id))
                            {
                                break label59;
                            }
                        }
                        return false;
                    }

                    Object this$patologia = this.getPatologia();
                    Object other$patologia = other.getPatologia();
                    if (this$patologia == null)
                    {
                        if (other$patologia != null)
                        {
                            return false;
                        }
                    }
                    else
                    {
                        if (!this$patologia.equals(other$patologia))
                        {
                            return false;
                        }
                    }
                    Object this$alergias = this.getAlergias();
                    Object other$alergias = other.getAlergias();
                    if (this$alergias == null)
                    {
                        if (other$alergias != null)
                        {
                            return false;
                        }
                    }
                    else
                    {
                        if (!this$alergias.equals(other$alergias))
                        {
                            return false;
                        }
                    }
                    Object this$paciente = this.getPaciente();
                    Object other$paciente = other.getPaciente();
                    if (this$paciente == null) {
                        if (other$paciente != null) {
                            return false;
                        }
                    }
                    else
                    {
                        if (!this$paciente.equals(other$paciente))
                        {
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
    }

    protected boolean canEqual(final Object other)
    {
        return other instanceof AntecedentesPatologicosFamiliares;
    }

    public int hashCode()
    {
        //int PRIME = true;
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $patologia = this.getPatologia();
        result = result * 59 + ($patologia == null ? 43 : $patologia.hashCode());
        Object $alergias = this.getAlergias();
        result = result * 59 + ($alergias == null ? 43 : $alergias.hashCode());
        Object $paciente = this.getPaciente();
        result = result * 59 + ($paciente == null ? 43 : $paciente.hashCode());
        return result;
    }

    public String toString()
    {
        Long var10000 = this.getId();
        return "AntecedentesPatologicosFamiliares(id=" + var10000 + ", patologia=" + this.getPatologia() + ", alergias=" + this.getAlergias() + ", paciente=" + this.getPaciente() + ")";
    }

    public AntecedentesPatologicosFamiliares() {
    }
}
