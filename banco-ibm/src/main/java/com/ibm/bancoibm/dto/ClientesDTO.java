package com.ibm.bancoibm.dto;

import java.io.Serializable;


public class ClientesDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String ciudad;
    private Long clieId;
    private String direccion;
    private String nombre;
    private Long telefono;

    
    
    public ClientesDTO() {
		super();
    }
    

	public ClientesDTO(String ciudad, Long clieId, String direccion, String nombre, Long telefono) {
		super();
		this.ciudad = ciudad;
		this.clieId = clieId;
		this.direccion = direccion;
		this.nombre = nombre;
		this.telefono = telefono;
	}

	public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Long getClieId() {
        return clieId;
    }

    public void setClieId(Long clieId) {
        this.clieId = clieId;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }
    
    @Override
	public String toString() {
		return "ClientesDTO [ciudad=" + ciudad + ", clieId=" + clieId + ", direccion=" + direccion + ", nombre="
				+ nombre + ", telefono=" + telefono + "]";
	}
}
