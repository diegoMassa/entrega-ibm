package com.ibm.bancoibm.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "clientes", schema = "public")
public class Clientes implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long clieId;
    private String ciudad;
    private String direccion;
    private String nombre;
    private Long telefono;
    private Set<Tarjetas> tarjetases = new HashSet<Tarjetas>(0);

    public Clientes() {
    }

    public Clientes(Long clieId, String ciudad, String direccion,
        String nombre, Set<Tarjetas> tarjetases, Long telefono) {
        this.clieId = clieId;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.nombre = nombre;
        this.telefono = telefono;
        this.tarjetases = tarjetases;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clie_id", unique = true)
    public Long getClieId() {
        return this.clieId;
    }

    public void setClieId(Long clieId) {
        this.clieId = clieId;
    }

    @Column(name = "ciudad")
    public String getCiudad() {
        return this.ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Column(name = "direccion")
    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Column(name = "nombre")
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "telefono")
    public Long getTelefono() {
        return this.telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clientes")
    public Set<Tarjetas> getTarjetases() {
        return this.tarjetases;
    }

    public void setTarjetases(Set<Tarjetas> tarjetases) {
        this.tarjetases = tarjetases;
    }
}
