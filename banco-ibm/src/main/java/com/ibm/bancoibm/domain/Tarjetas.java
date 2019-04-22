package com.ibm.bancoibm.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "tarjetas", schema = "public")
public class Tarjetas implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long tarjeId;
    @NotNull
    private Clientes clientes;
    @NotNull
    private Long ccv;
    @NotNull
    private Long numeroTarjeta;
    @NotNull
    @NotEmpty
    @Size(max = 50)
    private String tipoTarjeta;
    private Set<Transacciones> transaccioneses = new HashSet<Transacciones>(0);

    public Tarjetas() {
    }

    public Tarjetas(Long tarjeId, Long ccv, Clientes clientes,
        Long numeroTarjeta, String tipoTarjeta,
        Set<Transacciones> transaccioneses) {
        this.tarjeId = tarjeId;
        this.clientes = clientes;
        this.ccv = ccv;
        this.numeroTarjeta = numeroTarjeta;
        this.tipoTarjeta = tipoTarjeta;
        this.transaccioneses = transaccioneses;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tarje_id", unique = true)
    public Long getTarjeId() {
        return this.tarjeId;
    }

    public void setTarjeId(Long tarjeId) {
        this.tarjeId = tarjeId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clie_id")
    public Clientes getClientes() {
        return this.clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    @Column(name = "ccv", nullable = false)
    public Long getCcv() {
        return this.ccv;
    }

    public void setCcv(Long ccv) {
        this.ccv = ccv;
    }

    @Column(name = "numero_tarjeta", nullable = false)
    public Long getNumeroTarjeta() {
        return this.numeroTarjeta;
    }

    public void setNumeroTarjeta(Long numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    @Column(name = "tipo_tarjeta", nullable = false)
    public String getTipoTarjeta() {
        return this.tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tarjetas")
    public Set<Transacciones> getTransaccioneses() {
        return this.transaccioneses;
    }

    public void setTransaccioneses(Set<Transacciones> transaccioneses) {
        this.transaccioneses = transaccioneses;
    }
}
