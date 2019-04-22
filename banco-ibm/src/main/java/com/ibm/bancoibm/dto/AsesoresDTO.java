package com.ibm.bancoibm.dto;

import java.io.Serializable;


public class AsesoresDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long asesId;
    private String especialidad;
    private String nombre;
    

    public AsesoresDTO() {
		super();
	}

	public AsesoresDTO(Long asesId, String especialidad, String nombre) {
		super();
		this.asesId = asesId;
		this.especialidad = especialidad;
		this.nombre = nombre;
	}

	public Long getAsesId() {
        return asesId;
    }

    public void setAsesId(Long asesId) {
        this.asesId = asesId;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

	@Override
	public String toString() {
		return "AsesoresDTO [asesId=" + asesId + ", especialidad=" + especialidad + ", nombre=" + nombre + "]";
	}
}
