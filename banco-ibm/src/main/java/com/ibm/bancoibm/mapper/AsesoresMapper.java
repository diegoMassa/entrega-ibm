package com.ibm.bancoibm.mapper;

import com.ibm.bancoibm.domain.Asesores;
import com.ibm.bancoibm.dto.AsesoresDTO;

import java.util.List;


public interface AsesoresMapper {
	
    public AsesoresDTO asesoresToAsesoresDTO(Asesores asesores)throws Exception;

    public Asesores asesoresDTOToAsesores(AsesoresDTO asesoresDTO)throws Exception;

    public List<AsesoresDTO> listAsesoresToListAsesoresDTO(List<Asesores> asesoress) throws Exception;

    public List<Asesores> listAsesoresDTOToListAsesores(List<AsesoresDTO> asesoresDTOs) throws Exception;
}
