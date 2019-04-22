package com.ibm.bancoibm.mapper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.bancoibm.domain.Clientes;
import com.ibm.bancoibm.domain.Tarjetas;
import com.ibm.bancoibm.dto.TarjetasDTO;
import com.ibm.bancoibm.service.ClientesService;


@Component
@Scope("singleton")
public class TarjetasMapperImpl implements TarjetasMapper {
    
	private static final Logger log = LoggerFactory.getLogger(TarjetasMapperImpl.class);

    @Autowired
    ClientesService serviceClientes1;

    @Transactional(readOnly = true)
    public TarjetasDTO tarjetasToTarjetasDTO(Tarjetas tarjetas)
        throws Exception {
        try {
            TarjetasDTO tarjetasDTO = new TarjetasDTO();

            tarjetasDTO.setTarjeId(tarjetas.getTarjeId());
            tarjetasDTO.setCcv((tarjetas.getCcv() != null) ? tarjetas.getCcv() : null);
            tarjetasDTO.setNumeroTarjeta((tarjetas.getNumeroTarjeta() != null) ? tarjetas.getNumeroTarjeta() : null);
            tarjetasDTO.setTipoTarjeta((tarjetas.getTipoTarjeta()  != null) ? tarjetas.getTipoTarjeta() : null);
            tarjetasDTO.setClieId_Clientes((tarjetas.getClientes() != null) ? tarjetas.getClientes().getClieId() : null);
            tarjetasDTO.setNombreCliente((tarjetas.getClientes()   != null) ? tarjetas.getClientes().getNombre() : null);
            return tarjetasDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Tarjetas tarjetasDTOToTarjetas(TarjetasDTO tarjetasDTO)
        throws Exception {
        try {
            Tarjetas tarjetas = new Tarjetas();

            tarjetas.setTarjeId(tarjetasDTO.getTarjeId());
            tarjetas.setCcv((tarjetasDTO.getCcv() != null)
                ? tarjetasDTO.getCcv() : null);
            tarjetas.setNumeroTarjeta((tarjetasDTO.getNumeroTarjeta() != null)
                ? tarjetasDTO.getNumeroTarjeta() : null);
            tarjetas.setTipoTarjeta((tarjetasDTO.getTipoTarjeta() != null)
                ? tarjetasDTO.getTipoTarjeta() : null);

            Clientes clientes = new Clientes();

            if (tarjetasDTO.getClieId_Clientes() != null) {
                clientes = serviceClientes1.getClientes(tarjetasDTO.getClieId_Clientes());
            }

            if (clientes != null) {
                tarjetas.setClientes(clientes);
            }

            return tarjetas;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<TarjetasDTO> listTarjetasToListTarjetasDTO(
        List<Tarjetas> listTarjetas) throws Exception {
        try {
            List<TarjetasDTO> tarjetasDTOs = new ArrayList<TarjetasDTO>();

            for (Tarjetas tarjetas : listTarjetas) {
                TarjetasDTO tarjetasDTO = tarjetasToTarjetasDTO(tarjetas);

                tarjetasDTOs.add(tarjetasDTO);
            }

            return tarjetasDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<Tarjetas> listTarjetasDTOToListTarjetas(
        List<TarjetasDTO> listTarjetasDTO) throws Exception {
        try {
            List<Tarjetas> listTarjetas = new ArrayList<Tarjetas>();

            for (TarjetasDTO tarjetasDTO : listTarjetasDTO) {
                Tarjetas tarjetas = tarjetasDTOToTarjetas(tarjetasDTO);

                listTarjetas.add(tarjetas);
            }

            return listTarjetas;
        } catch (Exception e) {
            throw e;
        }
    }
}
