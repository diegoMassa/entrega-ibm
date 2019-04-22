package com.ibm.bancoibm.mapper;

import com.ibm.bancoibm.domain.Tarjetas;
import com.ibm.bancoibm.dto.TarjetasDTO;

import java.util.List;


public interface TarjetasMapper {
	
    public TarjetasDTO tarjetasToTarjetasDTO(Tarjetas tarjetas) throws Exception;

    public Tarjetas tarjetasDTOToTarjetas(TarjetasDTO tarjetasDTO) throws Exception;

    public List<TarjetasDTO> listTarjetasToListTarjetasDTO(List<Tarjetas> tarjetass) throws Exception;

    public List<Tarjetas> listTarjetasDTOToListTarjetas(List<TarjetasDTO> tarjetasDTOs) throws Exception;
}
