package com.ibm.bancoibm.service;

import java.util.List;

import com.ibm.bancoibm.domain.Clientes;
import com.ibm.bancoibm.dto.ClientesDTO;


public interface ClientesService {
	
    public List<Clientes> getClientes() throws Exception;

    public void saveClientes(Clientes entity) throws Exception;

    public void deleteClientes(Clientes entity) throws Exception;

    public void updateClientes(Clientes entity) throws Exception;

    public Clientes getClientes(Long clieId) throws Exception;

    public List<Clientes> findByCriteria(Object[] variables, Object[] variablesBetween, Object[] variablesBetweenDates) throws Exception;

    public List<Clientes> findPageClientes(String sortColumnName, boolean sortAscending, int startRow, int maxResults) throws Exception;

    public Long findTotalNumberClientes() throws Exception;

    public List<ClientesDTO> getDataClientes() throws Exception;

    public void validateClientes(Clientes clientes) throws Exception;
}