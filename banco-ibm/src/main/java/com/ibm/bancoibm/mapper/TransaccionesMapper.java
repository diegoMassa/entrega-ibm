package com.ibm.bancoibm.mapper;

import com.ibm.bancoibm.domain.Transacciones;
import com.ibm.bancoibm.dto.TransaccionesDTO;

import java.util.List;


public interface TransaccionesMapper {
	
    public TransaccionesDTO transaccionesToTransaccionesDTO(Transacciones transacciones) throws Exception;

    public Transacciones transaccionesDTOToTransacciones(TransaccionesDTO transaccionesDTO) throws Exception;

    public List<TransaccionesDTO> listTransaccionesToListTransaccionesDTO(List<Transacciones> transaccioness) throws Exception;

    public List<Transacciones> listTransaccionesDTOToListTransacciones(List<TransaccionesDTO> transaccionesDTOs) throws Exception;
}
