package com.ibm.bancoibm.domain;

import java.util.Date;

import javax.persistence.*;

import javax.validation.constraints.*;


@Entity
@Table(name = "transacciones", schema = "public")
public class Transacciones implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long tranId;
    
	@NotNull
    private Tarjetas tarjetas;
    
	private String descripcion;
    
	@NotNull
    private Date fecha;
    
	@NotNull
    private Long monto;

    public Transacciones() {
    }

    public Transacciones(Long tranId, String descripcion, Date fecha,
        Long monto, Tarjetas tarjetas) {
        this.tranId = tranId;
        this.tarjetas = tarjetas;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.monto = monto;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tran_id", unique = true)
    public Long getTranId() {
        return this.tranId;
    }

    public void setTranId(Long tranId) {
        this.tranId = tranId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tarje_id")
    public Tarjetas getTarjetas() {
        return this.tarjetas;
    }

    public void setTarjetas(Tarjetas tarjetas) {
        this.tarjetas = tarjetas;
    }

    @Column(name = "descripcion")
    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Column(name = "fecha", nullable = false)
    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Column(name = "monto", nullable = false)
    public Long getMonto() {
        return this.monto;
    }

    public void setMonto(Long monto) {
        this.monto = monto;
    }
}
