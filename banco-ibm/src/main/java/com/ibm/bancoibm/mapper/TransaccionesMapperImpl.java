package com.ibm.bancoibm.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.bancoibm.domain.Tarjetas;
import com.ibm.bancoibm.domain.Transacciones;
import com.ibm.bancoibm.dto.TransaccionesDTO;
import com.ibm.bancoibm.service.TarjetasService;
import com.ibm.bancoibm.utility.Fechas;


@Component
@Scope("singleton")
public class TransaccionesMapperImpl implements TransaccionesMapper {
   
    @Autowired
    TarjetasService serviceTarjetas1;

    @Transactional(readOnly = true)
    public TransaccionesDTO transaccionesToTransaccionesDTO(
        Transacciones transacciones) throws Exception {
        try {
            TransaccionesDTO transaccionesDTO = new TransaccionesDTO();
            transaccionesDTO.setTranId(transacciones.getTranId());
            transaccionesDTO.setDescripcion((transacciones.getDescripcion() != null) ? transacciones.getDescripcion() : null);
            transaccionesDTO.setFecha(transacciones.getFecha());
            transaccionesDTO.setFechaCadena(transacciones.getFecha() != null ? Fechas.dateToStr(transacciones.getFecha(), "dd/MM/yyyy") : null);
            transaccionesDTO.setMonto((transacciones.getMonto() != null) ? transacciones.getMonto() : null);
           
            //Datos tarjeta
            transaccionesDTO.setTarjeId_Tarjetas((transacciones.getTarjetas() != null) ? transacciones.getTarjetas().getTarjeId() : null);
            transaccionesDTO.setNumeroTarjeta((transacciones.getTarjetas() != null) ? transacciones.getTarjetas().getNumeroTarjeta() : null);
            transaccionesDTO.setCcv((transacciones.getTarjetas() != null) ? transacciones.getTarjetas().getCcv() : null);
            transaccionesDTO.setTipoTarjeta((transacciones.getTarjetas() != null) ? transacciones.getTarjetas().getTipoTarjeta() : null);
            
            
            //Datos del cliente
            transaccionesDTO.setIdCliente((transacciones.getTarjetas() != null && transacciones.getTarjetas().getClientes() != null) ? transacciones.getTarjetas().getClientes().getClieId() : null);
            transaccionesDTO.setNombreCliente((transacciones.getTarjetas() != null && transacciones.getTarjetas().getClientes() != null) ? transacciones.getTarjetas().getClientes().getNombre() : null);
            return transaccionesDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Transacciones transaccionesDTOToTransacciones(
        TransaccionesDTO transaccionesDTO) throws Exception {
        try {
            Transacciones transacciones = new Transacciones();

            transacciones.setTranId(transaccionesDTO.getTranId());
            transacciones.setDescripcion((transaccionesDTO.getDescripcion() != null) ? transaccionesDTO.getDescripcion() : null);
            transacciones.setFecha(transaccionesDTO.getFecha());
            transacciones.setMonto((transaccionesDTO.getMonto() != null) ? transaccionesDTO.getMonto() : null);

            Tarjetas tarjetas = new Tarjetas();

            if (transaccionesDTO.getTarjeId_Tarjetas() != null) {
                tarjetas = serviceTarjetas1.getTarjetas(transaccionesDTO.getTarjeId_Tarjetas());
            }

            if (tarjetas != null) {
                transacciones.setTarjetas(tarjetas);
            }

            return transacciones;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<TransaccionesDTO> listTransaccionesToListTransaccionesDTO(
        List<Transacciones> listTransacciones) throws Exception {
        try {
            List<TransaccionesDTO> transaccionesDTOs = new ArrayList<TransaccionesDTO>();

            for (Transacciones transacciones : listTransacciones) {
                TransaccionesDTO transaccionesDTO = transaccionesToTransaccionesDTO(transacciones);

                transaccionesDTOs.add(transaccionesDTO);
            }

            return transaccionesDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<Transacciones> listTransaccionesDTOToListTransacciones(
        List<TransaccionesDTO> listTransaccionesDTO) throws Exception {
        try {
            List<Transacciones> listTransacciones = new ArrayList<Transacciones>();

            for (TransaccionesDTO transaccionesDTO : listTransaccionesDTO) {
                Transacciones transacciones = transaccionesDTOToTransacciones(transaccionesDTO);

                listTransacciones.add(transacciones);
            }

            return listTransacciones;
        } catch (Exception e) {
            throw e;
        }
    }
}
