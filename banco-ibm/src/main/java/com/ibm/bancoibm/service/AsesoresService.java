package com.ibm.bancoibm.service;

import java.util.List;

import com.ibm.bancoibm.domain.Asesores;
import com.ibm.bancoibm.dto.AsesoresDTO;


public interface AsesoresService {
    public List<Asesores> getAsesores() throws Exception;

    public void saveAsesores(Asesores entity) throws Exception;

    public void deleteAsesores(Asesores entity) throws Exception;

    public void updateAsesores(Asesores entity) throws Exception;

    public Asesores getAsesores(Long asesId) throws Exception;

    public List<Asesores> findByCriteria(Object[] variables, Object[] variablesBetween, Object[] variablesBetweenDates) throws Exception;

    public List<Asesores> findPageAsesores(String sortColumnName, boolean sortAscending, int startRow, int maxResults) throws Exception;

    public Long findTotalNumberAsesores() throws Exception;

    public List<AsesoresDTO> getDataAsesores() throws Exception;

    public void validateAsesores(Asesores asesores) throws Exception;
}
