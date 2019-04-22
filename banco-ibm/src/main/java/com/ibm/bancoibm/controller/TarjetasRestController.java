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

import com.ibm.bancoibm.domain.Tarjetas;
import com.ibm.bancoibm.dto.TarjetasDTO;
import com.ibm.bancoibm.dto.ClientesDTO;
import com.ibm.bancoibm.dto.GenericResponse;
import com.ibm.bancoibm.mapper.TarjetasMapper;
import com.ibm.bancoibm.service.TarjetasService;

@CrossOrigin("*")
@RestController
@RequestMapping("/tarjetas")
public class TarjetasRestController {
   
	private static final Logger log = LoggerFactory.getLogger(TarjetasRestController.class);
    
	@Autowired
    private TarjetasService tarjetasService;
    
	@Autowired
    private TarjetasMapper tarjetasMapper;

    @PostMapping(value = "/saveTarjetas")
    public ResponseEntity<GenericResponse<TarjetasDTO>> saveTarjetas(@RequestBody TarjetasDTO tarjetasDTO) throws Exception {
       
    	GenericResponse<TarjetasDTO> genericResponse = new GenericResponse<TarjetasDTO>();
    	
    	try {
            Tarjetas tarjetas = tarjetasMapper.tarjetasDTOToTarjetas(tarjetasDTO);

            tarjetasService.saveTarjetas(tarjetas);
            genericResponse.setLista(null);
            genericResponse.setCodigo(String.valueOf(HttpStatus.OK.value()));
            genericResponse.setMensaje(HttpStatus.OK.getReasonPhrase());
            return new ResponseEntity<GenericResponse<TarjetasDTO>>(genericResponse, HttpStatus.OK);
    	} catch (Exception e) {
    		e.printStackTrace();
    		 log.error(e.getMessage(), e);
    		genericResponse.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
    		genericResponse.setMensaje(e.getMessage());
    		return new ResponseEntity<GenericResponse<TarjetasDTO>>(genericResponse, HttpStatus.OK);
    	}

    }

    @DeleteMapping(value = "/deleteTarjetas/{tarjeId}")
    public ResponseEntity<GenericResponse<TarjetasDTO>> deleteTarjetas(@PathVariable("tarjeId") Long tarjeId) throws Exception {
       
    	GenericResponse<TarjetasDTO> genericResponse = new GenericResponse<TarjetasDTO>();
    	
    	try {
            Tarjetas tarjetas = tarjetasService.getTarjetas(tarjeId);

            tarjetasService.deleteTarjetas(tarjetas);
            genericResponse.setLista(null);
            genericResponse.setCodigo(String.valueOf(HttpStatus.OK.value()));
            genericResponse.setMensaje(HttpStatus.OK.getReasonPhrase());
            return new ResponseEntity<GenericResponse<TarjetasDTO>>(genericResponse, HttpStatus.OK);
    	} catch (Exception e) {
    		e.printStackTrace();
    		 log.error(e.getMessage(), e);
    		genericResponse.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
    		genericResponse.setMensaje(e.getMessage());
    		return new ResponseEntity<GenericResponse<TarjetasDTO>>(genericResponse, HttpStatus.OK);
    	}
    }

    @PutMapping(value = "/updateTarjetas")
    public ResponseEntity<GenericResponse<TarjetasDTO>> updateTarjetas(@RequestBody TarjetasDTO tarjetasDTO) throws Exception {
       
    	GenericResponse<TarjetasDTO> genericResponse = new GenericResponse<TarjetasDTO>();
    	
    	try {
            Tarjetas tarjetas = tarjetasMapper.tarjetasDTOToTarjetas(tarjetasDTO);

            tarjetasService.updateTarjetas(tarjetas);
            genericResponse.setLista(null);
            genericResponse.setCodigo(String.valueOf(HttpStatus.OK.value()));
            genericResponse.setMensaje(HttpStatus.OK.getReasonPhrase());
            return new ResponseEntity<GenericResponse<TarjetasDTO>>(genericResponse, HttpStatus.OK);
    	} catch (Exception e) {
    		e.printStackTrace();
    		 log.error(e.getMessage(), e);
    		genericResponse.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
    		genericResponse.setMensaje(e.getMessage());
    		return new ResponseEntity<GenericResponse<TarjetasDTO>>(genericResponse, HttpStatus.OK);
    	}
    }

    @GetMapping(value = "/getDataTarjetas")
    public ResponseEntity<GenericResponse<TarjetasDTO>> getDataTarjetas() throws Exception {
       
    	GenericResponse<TarjetasDTO> genericResponse = new GenericResponse<TarjetasDTO>();
    	
    	try {
            genericResponse.setLista(tarjetasService.getDataTarjetas());
            genericResponse.setCodigo(String.valueOf(HttpStatus.OK.value()));
            genericResponse.setMensaje(HttpStatus.OK.getReasonPhrase());
            return new ResponseEntity<GenericResponse<TarjetasDTO>>(genericResponse, HttpStatus.OK);
    	} catch (Exception e) {
    		e.printStackTrace();
    		 log.error(e.getMessage(), e);
    		genericResponse.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
    		genericResponse.setMensaje(e.getMessage());
    		return new ResponseEntity<GenericResponse<TarjetasDTO>>(genericResponse, HttpStatus.OK);
    	}
    }
    
    @PostMapping(value = "/getTarjetasCliente")
    public ResponseEntity<GenericResponse<TarjetasDTO>> getTarjetasCliente(@RequestBody ClientesDTO cliente) throws Exception {
       
    	GenericResponse<TarjetasDTO> genericResponse = new GenericResponse<TarjetasDTO>();
    	
    	try {
            genericResponse.setLista(tarjetasMapper.listTarjetasToListTarjetasDTO(tarjetasService.findByCriteria(new Object[] {"clientes.clieId", false, cliente.getClieId(), "="}, null, null)));
            genericResponse.setCodigo(String.valueOf(HttpStatus.OK.value()));
            genericResponse.setMensaje(HttpStatus.OK.getReasonPhrase());
            return new ResponseEntity<GenericResponse<TarjetasDTO>>(genericResponse, HttpStatus.OK);
    	} catch (Exception e) {
    		e.printStackTrace();
    		 log.error(e.getMessage(), e);
    		genericResponse.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
    		genericResponse.setMensaje(e.getMessage());
    		return new ResponseEntity<GenericResponse<TarjetasDTO>>(genericResponse, HttpStatus.OK);
    	}

    }

    @GetMapping(value = "/getTarjetas/{tarjeId}")
    public ResponseEntity<GenericResponse<TarjetasDTO>> getTarjetas(@PathVariable("tarjeId") Long tarjeId) throws Exception {
       
    	GenericResponse<TarjetasDTO> genericResponse = new GenericResponse<TarjetasDTO>();
    	
    	try {
            Tarjetas tarjetas = tarjetasService.getTarjetas(tarjeId);
            
    		List<TarjetasDTO> listTarjetasDTO = null;
    		
    		if (tarjetas != null) {
    			listTarjetasDTO = new ArrayList<TarjetasDTO>();
    			TarjetasDTO tarjetasDTO = tarjetasMapper.tarjetasToTarjetasDTO(tarjetas);
    			listTarjetasDTO.add(tarjetasDTO);
			}
            genericResponse.setLista(listTarjetasDTO);
            genericResponse.setCodigo(String.valueOf(HttpStatus.OK.value()));
            genericResponse.setMensaje(HttpStatus.OK.getReasonPhrase());
            return new ResponseEntity<GenericResponse<TarjetasDTO>>(genericResponse, HttpStatus.OK);
    	} catch (Exception e) {
    		e.printStackTrace();
    		 log.error(e.getMessage(), e);
    		genericResponse.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
    		genericResponse.setMensaje(e.getMessage());
    		return new ResponseEntity<GenericResponse<TarjetasDTO>>(genericResponse, HttpStatus.OK);
    	}
    }
    
    
    @GetMapping(value = "/getTarjetasByClieId/{clieId}")
    public ResponseEntity<GenericResponse<TarjetasDTO>> getTarjetasByClieId(@PathVariable("clieId") Long clieId) throws Exception {
       
    	GenericResponse<TarjetasDTO> genericResponse = new GenericResponse<TarjetasDTO>();
    	List<TarjetasDTO> listTarjetasDTO = null;
    	
    	try {
            List<Tarjetas> tarjetasDeCliente = tarjetasService.getTarjetasByClieId(clieId);
    		
    		if (tarjetasDeCliente != null) {
    			listTarjetasDTO = new ArrayList<TarjetasDTO>();
    			for (Tarjetas tarjetas : tarjetasDeCliente) {
    				TarjetasDTO tarjetasDTO = tarjetasMapper.tarjetasToTarjetasDTO(tarjetas);
    				listTarjetasDTO.add(tarjetasDTO);
				}
			}
            genericResponse.setLista(listTarjetasDTO);
            genericResponse.setCodigo(String.valueOf(HttpStatus.OK.value()));
            genericResponse.setMensaje(HttpStatus.OK.getReasonPhrase());
            return new ResponseEntity<GenericResponse<TarjetasDTO>>(genericResponse, HttpStatus.OK);
    	} catch (Exception e) {
    		e.printStackTrace();
    		 log.error(e.getMessage(), e);
    		genericResponse.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
    		genericResponse.setMensaje(e.getMessage());
    		return new ResponseEntity<GenericResponse<TarjetasDTO>>(genericResponse, HttpStatus.OK);
    	}
    }
}