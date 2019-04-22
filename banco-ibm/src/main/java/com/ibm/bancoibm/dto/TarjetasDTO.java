package com.ibm.bancoibm.dto;

import java.io.Serializable;


public class TarjetasDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long ccv;
    private Long numeroTarjeta;
    private Long tarjeId;
    private String tipoTarjeta;
    private Long clieId_Clientes;
    private String nombreCliente;
    
    public TarjetasDTO() {
		super();
	}
    
	public TarjetasDTO(Long ccv, Long numeroTarjeta, Long tarjeId, String tipoTarjeta, Long clieId_Clientes, String nombreCliente) {
		super();
		this.ccv = ccv;
		this.numeroTarjeta = numeroTarjeta;
		this.tarjeId = tarjeId;
		this.tipoTarjeta = tipoTarjeta;
		this.clieId_Clientes = clieId_Clientes;
		this.nombreCliente = nombreCliente;
	}

	public Long getCcv() {
        return ccv;
    }

    public void setCcv(Long ccv) {
        this.ccv = ccv;
    }

    public Long getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(Long numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public Long getTarjeId() {
        return tarjeId;
    }

    public void setTarjeId(Long tarjeId) {
        this.tarjeId = tarjeId;
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public Long getClieId_Clientes() {
        return clieId_Clientes;
    }

    public void setClieId_Clientes(Long clieId_Clientes) {
        this.clieId_Clientes = clieId_Clientes;
    }

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	@Override
	public String toString() {
		return "TarjetasDTO [ccv=" + ccv + ", numeroTarjeta=" + numeroTarjeta + ", tarjeId=" + tarjeId
				+ ", tipoTarjeta=" + tipoTarjeta + ", clieId_Clientes=" + clieId_Clientes + "]";
	}
}