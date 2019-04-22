package com.ibm.bancoibm.service;

import java.util.List;

import com.ibm.bancoibm.domain.Tarjetas;
import com.ibm.bancoibm.dto.TarjetasDTO;


public interface TarjetasService {
	
    public List<Tarjetas> getTarjetas() throws Exception;

    public void saveTarjetas(Tarjetas entity) throws Exception;

    public void deleteTarjetas(Tarjetas entity) throws Exception;

    public void updateTarjetas(Tarjetas entity) throws Exception;

    public Tarjetas getTarjetas(Long tarjeId) throws Exception;

    public List<Tarjetas> findByCriteria(Object[] variables, Object[] variablesBetween, Object[] variablesBetweenDates) throws Exception;

    public List<Tarjetas> findPageTarjetas(String sortColumnName, boolean sortAscending, int startRow, int maxResults) throws Exception;

    public Long findTotalNumberTarjetas() throws Exception;

    public List<TarjetasDTO> getDataTarjetas() throws Exception;

    public void validateTarjetas(Tarjetas tarjetas) throws Exception;

	public List<Tarjetas> getTarjetasByClieId(Long clieId) throws Exception;
}
