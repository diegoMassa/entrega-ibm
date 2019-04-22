package com.ibm.bancoibm.controller;

import com.ibm.bancoibm.domain.*;
import com.ibm.bancoibm.dto.GenericResponse;
import com.ibm.bancoibm.dto.TarjetasDTO;
import com.ibm.bancoibm.dto.TransaccionesDTO;
import com.ibm.bancoibm.mapper.TransaccionesMapper;
import com.ibm.bancoibm.service.TransaccionesService;

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

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/transacciones")
public class TransaccionesRestController {
   
	private static final Logger log = LoggerFactory.getLogger(TransaccionesRestController.class);
    
	@Autowired
    private TransaccionesService transaccionesService;
    
	@Autowired
    private TransaccionesMapper transaccionesMapper;

    @PostMapping(value = "/saveTransacciones")
    public ResponseEntity<GenericResponse<TransaccionesDTO>> saveTransacciones(@RequestBody TransaccionesDTO transaccionesDTO) throws Exception {
       
    	GenericResponse<TransaccionesDTO> genericResponse = new GenericResponse<TransaccionesDTO>();
    	
    	try {
            Transacciones transacciones = transaccionesMapper.transaccionesDTOToTransacciones(transaccionesDTO);

            transaccionesService.saveTransacciones(transacciones);
            genericResponse.setLista(null);
            genericResponse.setCodigo(String.valueOf(HttpStatus.OK.value()));
            genericResponse.setMensaje(HttpStatus.OK.getReasonPhrase());
            return new ResponseEntity<GenericResponse<TransaccionesDTO>>(genericResponse, HttpStatus.OK);
    	} catch (Exception e) {
    		e.printStackTrace();
    		 log.error(e.getMessage(), e);
    		genericResponse.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
    		genericResponse.setMensaje(e.getMessage());
    		return new ResponseEntity<GenericResponse<TransaccionesDTO>>(genericResponse, HttpStatus.OK);
    	}
    }

    @DeleteMapping(value = "/deleteTransacciones/{tranId}")
    public ResponseEntity<GenericResponse<TransaccionesDTO>> deleteTransacciones(@PathVariable("tranId") Long tranId) throws Exception {
        
    	GenericResponse<TransaccionesDTO> genericResponse = new GenericResponse<TransaccionesDTO>();
    	
    	try {
            Transacciones transacciones = transaccionesService.getTransacciones(tranId);

            transaccionesService.deleteTransacciones(transacciones);
            genericResponse.setLista(null);
            genericResponse.setCodigo(String.valueOf(HttpStatus.OK.value()));
            genericResponse.setMensaje(HttpStatus.OK.getReasonPhrase());
            return new ResponseEntity<GenericResponse<TransaccionesDTO>>(genericResponse, HttpStatus.OK);
    	} catch (Exception e) {
    		e.printStackTrace();
    		 log.error(e.getMessage(), e);
    		genericResponse.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
    		genericResponse.setMensaje(e.getMessage());
    		return new ResponseEntity<GenericResponse<TransaccionesDTO>>(genericResponse, HttpStatus.OK);
    	}
    }

    @PutMapping(value = "/updateTransacciones")
    public ResponseEntity<GenericResponse<TransaccionesDTO>> updateTransacciones(@RequestBody TransaccionesDTO transaccionesDTO) throws Exception {
       
    	GenericResponse<TransaccionesDTO> genericResponse = new GenericResponse<TransaccionesDTO>();
    	
    	try {
            Transacciones transacciones = transaccionesMapper.transaccionesDTOToTransacciones(transaccionesDTO);

            transaccionesService.updateTransacciones(transacciones);
            genericResponse.setLista(null);
            genericResponse.setCodigo(String.valueOf(HttpStatus.OK.value()));
            genericResponse.setMensaje(HttpStatus.OK.getReasonPhrase());
            return new ResponseEntity<GenericResponse<TransaccionesDTO>>(genericResponse, HttpStatus.OK);
    	} catch (Exception e) {
    		e.printStackTrace();
    		 log.error(e.getMessage(), e);
    		genericResponse.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
    		genericResponse.setMensaje(e.getMessage());
    		return new ResponseEntity<GenericResponse<TransaccionesDTO>>(genericResponse, HttpStatus.OK);
    	}
    }

    @GetMapping(value = "/getDataTransacciones")
    public ResponseEntity<GenericResponse<TransaccionesDTO>> getDataTransacciones() throws Exception {
        
    	GenericResponse<TransaccionesDTO> genericResponse = new GenericResponse<TransaccionesDTO>();
    	
    	try {
            genericResponse.setLista(transaccionesService.getDataTransacciones());
            genericResponse.setCodigo(String.valueOf(HttpStatus.OK.value()));
            genericResponse.setMensaje(HttpStatus.OK.getReasonPhrase());
            return new ResponseEntity<GenericResponse<TransaccionesDTO>>(genericResponse, HttpStatus.OK);
    	} catch (Exception e) {
    		e.printStackTrace();
    		 log.error(e.getMessage(), e);
    		genericResponse.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
    		genericResponse.setMensaje(e.getMessage());
    		return new ResponseEntity<GenericResponse<TransaccionesDTO>>(genericResponse, HttpStatus.OK);
    	}
    }
    
    @PostMapping(value = "/getTransaccionesTarjeta")
    public ResponseEntity<GenericResponse<TransaccionesDTO>> getTransaccionesTarjeta(@RequestBody TarjetasDTO tarjeta) throws Exception {
       
    	GenericResponse<TransaccionesDTO> genericResponse = new GenericResponse<TransaccionesDTO>();
    	
    	try {
            genericResponse.setLista(transaccionesMapper.listTransaccionesToListTransaccionesDTO(transaccionesService.findByCriteria(new Object[] {"tarjetas.tarjeId", false, tarjeta.getTarjeId(), "="}, null, null)));
            genericResponse.setCodigo(String.valueOf(HttpStatus.OK.value()));
            genericResponse.setMensaje(HttpStatus.OK.getReasonPhrase());
            return new ResponseEntity<GenericResponse<TransaccionesDTO>>(genericResponse, HttpStatus.OK);
    	} catch (Exception e) {
    		e.printStackTrace();
    		 log.error(e.getMessage(), e);
    		genericResponse.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
    		genericResponse.setMensaje(e.getMessage());
    		return new ResponseEntity<GenericResponse<TransaccionesDTO>>(genericResponse, HttpStatus.OK);
    	}
    }

    @GetMapping(value = "/getTransacciones/{tranId}")
    public ResponseEntity<GenericResponse<TransaccionesDTO>> getTransacciones(@PathVariable("tranId") Long tranId) throws Exception {
       
    	GenericResponse<TransaccionesDTO> genericResponse = new GenericResponse<TransaccionesDTO>();
    	
    	try {
            Transacciones transacciones = transaccionesService.getTransacciones(tranId);

            List<TransaccionesDTO> listtransaccionesDTO = null;
    		
    		if (transacciones != null) {
    			listtransaccionesDTO = new ArrayList<TransaccionesDTO>();
    			TransaccionesDTO transaccionesDTO = transaccionesMapper.transaccionesToTransaccionesDTO(transacciones);
    			listtransaccionesDTO.add(transaccionesDTO);
			}
            genericResponse.setLista(listtransaccionesDTO);
            genericResponse.setCodigo(String.valueOf(HttpStatus.OK.value()));
            genericResponse.setMensaje(HttpStatus.OK.getReasonPhrase());
            return new ResponseEntity<GenericResponse<TransaccionesDTO>>(genericResponse, HttpStatus.OK);
    	} catch (Exception e) {
    		e.printStackTrace();
    		log.error(e.getMessage(), e);
    		genericResponse.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
    		genericResponse.setMensaje(e.getMessage());
    		return new ResponseEntity<GenericResponse<TransaccionesDTO>>(genericResponse, HttpStatus.OK);
    	}
    }
    
    
    @GetMapping(value = "/getTransaccionesByTarjId/{tarjId}")
    public ResponseEntity<GenericResponse<TransaccionesDTO>> getTransaccionesByTarjId(@PathVariable("tarjId") Long tarjId) throws Exception {
       
    	GenericResponse<TransaccionesDTO> genericResponse = new GenericResponse<TransaccionesDTO>();
    	List<TransaccionesDTO> listTransaccionesDTO = null;
    	
    	try {
            List<Transacciones> transaccionesDeTarjeta = transaccionesService.getTransaccionesByTarjId(tarjId);
    		
    		if (transaccionesDeTarjeta != null) {
    			listTransaccionesDTO = new ArrayList<TransaccionesDTO>();
    			for (Transacciones transaccion : transaccionesDeTarjeta) {
    				TransaccionesDTO transaccionDTO = transaccionesMapper.transaccionesToTransaccionesDTO(transaccion);
    				listTransaccionesDTO.add(transaccionDTO);
				}
			}
            genericResponse.setLista(listTransaccionesDTO);
            genericResponse.setCodigo(String.valueOf(HttpStatus.OK.value()));
            genericResponse.setMensaje(HttpStatus.OK.getReasonPhrase());
            return new ResponseEntity<GenericResponse<TransaccionesDTO>>(genericResponse, HttpStatus.OK);
    	} catch (Exception e) {
    		e.printStackTrace();
    		 log.error(e.getMessage(), e);
    		genericResponse.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
    		genericResponse.setMensaje(e.getMessage());
    		return new ResponseEntity<GenericResponse<TransaccionesDTO>>(genericResponse, HttpStatus.OK);
    	}
    }
}