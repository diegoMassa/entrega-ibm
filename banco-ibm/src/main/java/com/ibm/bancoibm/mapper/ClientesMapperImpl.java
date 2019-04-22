package com.ibm.bancoibm.mapper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.bancoibm.domain.Clientes;
import com.ibm.bancoibm.dto.ClientesDTO;


@Component
@Scope("singleton")
public class ClientesMapperImpl implements ClientesMapper {
    private static final Logger log = LoggerFactory.getLogger(ClientesMapperImpl.class);

    @Transactional(readOnly = true)
    public ClientesDTO clientesToClientesDTO(Clientes clientes)
        throws Exception {
        try {
            ClientesDTO clientesDTO = new ClientesDTO();

            clientesDTO.setClieId(clientes.getClieId());
            clientesDTO.setCiudad((clientes.getCiudad() != null)
                ? clientes.getCiudad() : null);
            clientesDTO.setDireccion((clientes.getDireccion() != null)
                ? clientes.getDireccion() : null);
            clientesDTO.setNombre((clientes.getNombre() != null)
                ? clientes.getNombre() : null);
            clientesDTO.setTelefono((clientes.getTelefono() != null)
                ? clientes.getTelefono() : null);

            return clientesDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Clientes clientesDTOToClientes(ClientesDTO clientesDTO)
        throws Exception {
        try {
            Clientes clientes = new Clientes();

            clientes.setClieId(clientesDTO.getClieId());
            clientes.setCiudad((clientesDTO.getCiudad() != null)
                ? clientesDTO.getCiudad() : null);
            clientes.setDireccion((clientesDTO.getDireccion() != null)
                ? clientesDTO.getDireccion() : null);
            clientes.setNombre((clientesDTO.getNombre() != null)
                ? clientesDTO.getNombre() : null);
            clientes.setTelefono((clientesDTO.getTelefono() != null)
                ? clientesDTO.getTelefono() : null);

            return clientes;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<ClientesDTO> listClientesToListClientesDTO(
        List<Clientes> listClientes) throws Exception {
        try {
            List<ClientesDTO> clientesDTOs = new ArrayList<ClientesDTO>();

            for (Clientes clientes : listClientes) {
                ClientesDTO clientesDTO = clientesToClientesDTO(clientes);

                clientesDTOs.add(clientesDTO);
            }

            return clientesDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<Clientes> listClientesDTOToListClientes(
        List<ClientesDTO> listClientesDTO) throws Exception {
        try {
            List<Clientes> listClientes = new ArrayList<Clientes>();

            for (ClientesDTO clientesDTO : listClientesDTO) {
                Clientes clientes = clientesDTOToClientes(clientesDTO);

                listClientes.add(clientes);
            }

            return listClientes;
        } catch (Exception e) {
            throw e;
        }
    }
}
