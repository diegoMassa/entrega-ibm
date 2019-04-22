package com.ibm.bancoibm.mapper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.bancoibm.domain.Asesores;
import com.ibm.bancoibm.dto.AsesoresDTO;


@Component
@Scope("singleton")
public class AsesoresMapperImpl implements AsesoresMapper {
    private static final Logger log = LoggerFactory.getLogger(AsesoresMapperImpl.class);

    @Transactional(readOnly = true)
    public AsesoresDTO asesoresToAsesoresDTO(Asesores asesores)
        throws Exception {
        try {
            AsesoresDTO asesoresDTO = new AsesoresDTO();

            asesoresDTO.setAsesId(asesores.getAsesId());
            asesoresDTO.setEspecialidad((asesores.getEspecialidad() != null)
                ? asesores.getEspecialidad() : null);
            asesoresDTO.setNombre((asesores.getNombre() != null)
                ? asesores.getNombre() : null);

            return asesoresDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Asesores asesoresDTOToAsesores(AsesoresDTO asesoresDTO)
        throws Exception {
        try {
            Asesores asesores = new Asesores();

            asesores.setAsesId(asesoresDTO.getAsesId());
            asesores.setEspecialidad((asesoresDTO.getEspecialidad() != null)
                ? asesoresDTO.getEspecialidad() : null);
            asesores.setNombre((asesoresDTO.getNombre() != null)
                ? asesoresDTO.getNombre() : null);

            return asesores;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<AsesoresDTO> listAsesoresToListAsesoresDTO(
        List<Asesores> listAsesores) throws Exception {
        try {
            List<AsesoresDTO> asesoresDTOs = new ArrayList<AsesoresDTO>();

            for (Asesores asesores : listAsesores) {
                AsesoresDTO asesoresDTO = asesoresToAsesoresDTO(asesores);

                asesoresDTOs.add(asesoresDTO);
            }

            return asesoresDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<Asesores> listAsesoresDTOToListAsesores(
        List<AsesoresDTO> listAsesoresDTO) throws Exception {
        try {
            List<Asesores> listAsesores = new ArrayList<Asesores>();

            for (AsesoresDTO asesoresDTO : listAsesoresDTO) {
                Asesores asesores = asesoresDTOToAsesores(asesoresDTO);

                listAsesores.add(asesores);
            }

            return listAsesores;
        } catch (Exception e) {
            throw e;
        }
    }
}
