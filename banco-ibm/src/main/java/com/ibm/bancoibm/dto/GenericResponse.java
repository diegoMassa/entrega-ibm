package com.ibm.bancoibm.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GenericResponse<T> {

	private String codigo;
	private String mensaje;
	private List<T> lista;
	private Page<T> page;

	public GenericResponse() {
		super();
	}

	public GenericResponse(String codigo, String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

	public GenericResponse(String codigo, String mensaje, List<T> lista) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
		this.lista = lista;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public List<T> getLista() {
		return lista;
	}

	public void setLista(List<T> lista) {
		this.lista = lista;
	}
	
	public Page<T> getPage() {
		return page;
	}

	public void setPage(Page<T> page) {
		this.page = page;
	}
	
	@SuppressWarnings("hiding")
	public <T> List<T> listaToObjectList(Class<T> type) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		List<T> ts = mapper.readValue(mapper.writeValueAsString(this.lista), mapper.getTypeFactory().constructCollectionType(ArrayList.class, type));
		return ts;
	}

	@Override
	public String toString() {
		return "GenericResponse [codigo=" + codigo + ", mensaje=" + mensaje + ", lista=" + lista + "]";
	}
}
