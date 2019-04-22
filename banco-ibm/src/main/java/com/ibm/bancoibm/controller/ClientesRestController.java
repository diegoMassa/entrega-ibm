package com.ibm.bancoibm.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.bancoibm.domain.Clientes;
import com.ibm.bancoibm.dto.ClientesDTO;
import com.ibm.bancoibm.dto.GenericResponse;
import com.ibm.bancoibm.mapper.ClientesMapper;
import com.ibm.bancoibm.service.ClientesService;

@CrossOrigin("*")
@RestController
@RequestMapping("/clientes")
public class ClientesRestController {
    
	private static final Logger log = LoggerFactory.getLogger(ClientesRestController.class);
   
    @Autowired
    private ClientesService clientesService;
    
    @Autowired
    private ClientesMapper clientesMapper;

    @PostMapping(value = "/saveClientes")
    public ResponseEntity<GenericResponse<ClientesDTO>> saveClientes(@RequestBody ClientesDTO clientesDTO) throws Exception {
    	
    	GenericResponse<ClientesDTO> genericResponse = new GenericResponse<ClientesDTO>();
    	
    	try {
    		Clientes clientes = clientesMapper.clientesDTOToClientes(clientesDTO);
    		clientesService.saveClientes(clientes);
    		genericResponse.setLista(null);
    		genericResponse.setCodigo(String.valueOf(HttpStatus.OK.value()));
    		genericResponse.setMensaje(HttpStatus.OK.getReasonPhrase());
    		return new ResponseEntity<GenericResponse<ClientesDTO>>(genericResponse, HttpStatus.OK);
    	} catch (Exception e) {
    		e.printStackTrace();
    		log.error(e.getMessage(), e);
    		genericResponse.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
    		genericResponse.setMensaje(e.getMessage());
    		return new ResponseEntity<GenericResponse<ClientesDTO>>(genericResponse, HttpStatus.OK);
    	}
    }

    @DeleteMapping(value = "/deleteClientes/{clieId}")
    public ResponseEntity<GenericResponse<ClientesDTO>> deleteClientes(@PathVariable("clieId") Long clieId) throws Exception {
        
    	GenericResponse<ClientesDTO> genericResponse = new GenericResponse<ClientesDTO>();
    	
    	try {
            Clientes clientes = clientesService.getClientes(clieId);
            clientesService.deleteClientes(clientes);
            genericResponse.setLista(null);
            genericResponse.setCodigo(String.valueOf(HttpStatus.OK.value()));
            genericResponse.setMensaje(HttpStatus.OK.getReasonPhrase());
            return new ResponseEntity<GenericResponse<ClientesDTO>>(genericResponse, HttpStatus.OK);
    	} catch (Exception e) {
    		e.printStackTrace();
    		log.error(e.getMessage(), e);
    		genericResponse.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
    		genericResponse.setMensaje(e.getMessage());
    		return new ResponseEntity<GenericResponse<ClientesDTO>>(genericResponse, HttpStatus.OK);
    	}
    }

    @PutMapping(value = "/updateClientes")
    public ResponseEntity<GenericResponse<ClientesDTO>> updateClientes(@RequestBody ClientesDTO clientesDTO) throws Exception {
    	
    	GenericResponse<ClientesDTO> genericResponse = new GenericResponse<ClientesDTO>();
    	
        try {
            Clientes clientes = clientesMapper.clientesDTOToClientes(clientesDTO);
            clientesService.updateClientes(clientes);
            
            genericResponse.setLista(null);
			genericResponse.setCodigo(String.valueOf(HttpStatus.OK.value()));
			genericResponse.setMensaje(HttpStatus.OK.getReasonPhrase());
			return new ResponseEntity<GenericResponse<ClientesDTO>>(genericResponse, HttpStatus.OK);
        } catch (Exception e) {
        	e.printStackTrace();
        	log.error(e.getMessage(), e);
			genericResponse.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
			genericResponse.setMensaje(e.getMessage());
			return new ResponseEntity<GenericResponse<ClientesDTO>>(genericResponse, HttpStatus.OK);
		}
    }

    @GetMapping(value = "/getDataClientes")
    public ResponseEntity<GenericResponse<ClientesDTO>> getDataClientes() throws Exception {
      
    	GenericResponse<ClientesDTO> genericResponse = new GenericResponse<ClientesDTO>();
    	
    	try {
            genericResponse.setLista(clientesService.getDataClientes());
			genericResponse.setCodigo(String.valueOf(HttpStatus.OK.value()));
			genericResponse.setMensaje(HttpStatus.OK.getReasonPhrase());
			return new ResponseEntity<GenericResponse<ClientesDTO>>(genericResponse, HttpStatus.OK);
        } catch (Exception e) {
        	e.printStackTrace();
        	log.error(e.getMessage(), e);
			genericResponse.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
			genericResponse.setMensaje(e.getMessage());
			return new ResponseEntity<GenericResponse<ClientesDTO>>(genericResponse, HttpStatus.OK);
		}
    }

    @GetMapping(value = "/getClientes/{clieId}")
    public ResponseEntity<GenericResponse<ClientesDTO>> getClientes(@PathVariable("clieId") Long clieId) throws Exception {
      
    	GenericResponse<ClientesDTO> genericResponse = new GenericResponse<ClientesDTO>();
    	
    	try {
    		Clientes cliente = clientesService.getClientes(clieId);
    		List<ClientesDTO> listClientesDTO = null;
    		
    		if (cliente != null) {
    			listClientesDTO = new ArrayList<ClientesDTO>();
    			ClientesDTO clienteDTO = clientesMapper.clientesToClientesDTO(cliente);
    			listClientesDTO.add(clienteDTO);
			}
            genericResponse.setLista(listClientesDTO);
			genericResponse.setCodigo(String.valueOf(HttpStatus.OK.value()));
			genericResponse.setMensaje(HttpStatus.OK.getReasonPhrase());
			return new ResponseEntity<GenericResponse<ClientesDTO>>(genericResponse, HttpStatus.OK);
        } catch (Exception e) {
        	e.printStackTrace();
        	log.error(e.getMessage(), e);
			genericResponse.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
			genericResponse.setMensaje(e.getMessage());
			return new ResponseEntity<GenericResponse<ClientesDTO>>(genericResponse, HttpStatus.OK);
		}
    }
}
