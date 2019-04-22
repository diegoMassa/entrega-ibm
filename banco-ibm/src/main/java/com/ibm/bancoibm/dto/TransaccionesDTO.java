package com.ibm.bancoibm.dto;

import java.io.Serializable;
import java.util.Date;


public class TransaccionesDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    //Datos del consumo
    private Long tranId;
    private Date fecha;
    private String fechaCadena;
    private Long monto;
    private String descripcion;
    
    //Datos de la tarjeta
    private Long tarjeId_Tarjetas;
    private Long numeroTarjeta;
    private Long ccv;
    private String tipoTarjeta;
    
    //Datos del cliente
    private Long idCliente;
    private String nombreCliente;
    
    
    public TransaccionesDTO() {
		super();
	}
    
	public TransaccionesDTO(Long tranId, Date fecha, Long monto, String descripcion, Long tarjeId_Tarjetas,
			Long numeroTarjeta, Long ccv, String tipoTarjeta, Long idCliente, String nombreCliente, String fechaCadena) {
		super();
		this.tranId = tranId;
		this.fecha = fecha;
		this.monto = monto;
		this.descripcion = descripcion;
		this.tarjeId_Tarjetas = tarjeId_Tarjetas;
		this.numeroTarjeta = numeroTarjeta;
		this.ccv = ccv;
		this.tipoTarjeta = tipoTarjeta;
		this.idCliente = idCliente;
		this.nombreCliente = nombreCliente;
		this.fechaCadena = fechaCadena;
	}



	public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getMonto() {
        return monto;
    }

    public void setMonto(Long monto) {
        this.monto = monto;
    }

    public Long getTranId() {
        return tranId;
    }

    public void setTranId(Long tranId) {
        this.tranId = tranId;
    }

    public Long getTarjeId_Tarjetas() {
        return tarjeId_Tarjetas;
    }

    public void setTarjeId_Tarjetas(Long tarjeId_Tarjetas) {
        this.tarjeId_Tarjetas = tarjeId_Tarjetas;
    }
    
	public Long getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(Long numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	
	public Long getCcv() {
		return ccv;
	}

	public void setCcv(Long ccv) {
		this.ccv = ccv;
	}

	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getFechaCadena() {
		return fechaCadena;
	}

	public void setFechaCadena(String fechaCadena) {
		this.fechaCadena = fechaCadena;
	}

	@Override
	public String toString() {
		return "TransaccionesDTO [descripcion=" + descripcion + ", fecha=" + fecha + ", monto=" + monto + ", tranId="
				+ tranId + ", tarjeId_Tarjetas=" + tarjeId_Tarjetas + "]";
	}
}