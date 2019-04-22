package com.ibm.bancoibm.mapper;

import com.ibm.bancoibm.domain.Clientes;
import com.ibm.bancoibm.dto.ClientesDTO;

import java.util.List;


public interface ClientesMapper {
	
    public ClientesDTO clientesToClientesDTO(Clientes clientes)throws Exception;

    public Clientes clientesDTOToClientes(ClientesDTO clientesDTO)throws Exception;

    public List<ClientesDTO> listClientesToListClientesDTO(List<Clientes> clientess) throws Exception;

    public List<Clientes> listClientesDTOToListClientes(List<ClientesDTO> clientesDTOs) throws Exception;
}
