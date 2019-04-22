package com.ibm.bancoibm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "asesores", schema = "public")
public class Asesores implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long asesId;
    @NotNull
    @NotEmpty
    @Size(max = 50)
    private String especialidad;
    @NotNull
    @NotEmpty
    @Size(max = 50)
    private String nombre;

    public Asesores() {
    }

    public Asesores(Long asesId, String especialidad, String nombre) {
        this.asesId = asesId;
        this.especialidad = especialidad;
        this.nombre = nombre;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ases_id", unique = true)
    public Long getAsesId() {
        return this.asesId;
    }

    public void setAsesId(Long asesId) {
        this.asesId = asesId;
    }

    @Column(name = "especialidad", nullable = false)
    public String getEspecialidad() {
        return this.especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Column(name = "nombre", nullable = false)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
