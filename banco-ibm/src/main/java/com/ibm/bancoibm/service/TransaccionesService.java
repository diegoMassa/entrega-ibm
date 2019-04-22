package com.ibm.bancoibm.service;

import java.util.List;

import com.ibm.bancoibm.domain.Transacciones;
import com.ibm.bancoibm.dto.TransaccionesDTO;


public interface TransaccionesService {
    
	public List<Transacciones> getTransacciones() throws Exception;

    public void saveTransacciones(Transacciones entity) throws Exception;

    public void deleteTransacciones(Transacciones entity) throws Exception;

    public void updateTransacciones(Transacciones entity) throws Exception;

    public Transacciones getTransacciones(Long tranId) throws Exception;

    public List<Transacciones> findByCriteria(Object[] variables, Object[] variablesBetween, Object[] variablesBetweenDates) throws Exception;

    public List<Transacciones> findPageTransacciones(String sortColumnName, boolean sortAscending, int startRow, int maxResults) throws Exception;

    public Long findTotalNumberTransacciones() throws Exception;

    public List<TransaccionesDTO> getDataTransacciones() throws Exception;

    public void validateTransacciones(Transacciones transacciones) throws Exception;

	public List<Transacciones> getTransaccionesByTarjId(Long tarjId) throws Exception;
}
