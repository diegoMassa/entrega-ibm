package com.ibm.bancoibm.controller;

import java.util.ArrayList;
import java.util.Arrays;

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

import com.ibm.bancoibm.domain.Asesores;
import com.ibm.bancoibm.dto.AsesoresDTO;
import com.ibm.bancoibm.dto.GenericResponse;
import com.ibm.bancoibm.mapper.AsesoresMapper;
import com.ibm.bancoibm.service.AsesoresService;

@CrossOrigin("*")
@RestController
@RequestMapping("/asesores")
public class AsesoresRestController {
    
	private static final Logger log = LoggerFactory.getLogger(AsesoresRestController.class);
   
    @Autowired
    private AsesoresService asesoresService;
    
    @Autowired
    private AsesoresMapper asesoresMapper;

    @PostMapping(value = "/saveAsesores")
    public ResponseEntity<GenericResponse<AsesoresDTO>> saveAsesores(@RequestBody
    AsesoresDTO asesoresDTO) throws Exception {
    	
    	GenericResponse<AsesoresDTO> genericResponse = new GenericResponse<AsesoresDTO>();
    	
        try {
            Asesores asesores = asesoresMapper.asesoresDTOToAsesores(asesoresDTO);

            asesoresService.saveAsesores(asesores);
            
            genericResponse.setLista(null);
			genericResponse.setCodigo(String.valueOf(HttpStatus.OK.value()));
			genericResponse.setMensaje(HttpStatus.OK.getReasonPhrase());
			
			return new ResponseEntity<GenericResponse<AsesoresDTO>>(genericResponse, HttpStatus.OK);
        } catch (Exception e) {
        	e.printStackTrace();
        	log.error(e.getMessage(), e);
			genericResponse.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
			genericResponse.setMensaje(e.getMessage());
			return new ResponseEntity<GenericResponse<AsesoresDTO>>(genericResponse, HttpStatus.OK);
		}
    }
    

    @DeleteMapping(value = "/deleteAsesores/{asesId}")
    public ResponseEntity<GenericResponse<AsesoresDTO>> deleteAsesores(@PathVariable("asesId")
    Long asesId) throws Exception {
    	
    	GenericResponse<AsesoresDTO> genericResponse = new GenericResponse<AsesoresDTO>();
    	
        try {
            Asesores asesores = asesoresService.getAsesores(asesId);

            asesoresService.deleteAsesores(asesores);
            
            genericResponse.setLista(null);
			genericResponse.setCodigo(String.valueOf(HttpStatus.OK.value()));
			genericResponse.setMensaje(HttpStatus.OK.getReasonPhrase());
			
			return new ResponseEntity<GenericResponse<AsesoresDTO>>(genericResponse, HttpStatus.OK);
        } catch (Exception e) {
        	e.printStackTrace();
        	log.error(e.getMessage(), e);
			genericResponse.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
			genericResponse.setMensaje(e.getMessage());
			return new ResponseEntity<GenericResponse<AsesoresDTO>>(genericResponse, HttpStatus.OK);
		}
    }

    @PutMapping(value = "/updateAsesores")
    public ResponseEntity<GenericResponse<AsesoresDTO>> updateAsesores(@RequestBody
    AsesoresDTO asesoresDTO) throws Exception {
    	
    	GenericResponse<AsesoresDTO> genericResponse = new GenericResponse<AsesoresDTO>();
    	
        try {
            Asesores asesores = asesoresMapper.asesoresDTOToAsesores(asesoresDTO);

            asesoresService.updateAsesores(asesores);
            
            genericResponse.setLista(null);
			genericResponse.setCodigo(String.valueOf(HttpStatus.OK.value()));
			genericResponse.setMensaje(HttpStatus.OK.getReasonPhrase());
			
			return new ResponseEntity<GenericResponse<AsesoresDTO>>(genericResponse, HttpStatus.OK);
        } catch (Exception e) {
        	e.printStackTrace();
        	log.error(e.getMessage(), e);
			genericResponse.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
			genericResponse.setMensaje(e.getMessage());
			return new ResponseEntity<GenericResponse<AsesoresDTO>>(genericResponse, HttpStatus.OK);
		}
    }

    @GetMapping(value = "/getDataAsesores")
    public ResponseEntity<GenericResponse<AsesoresDTO>> getDataAsesores() throws Exception {
    	
    	GenericResponse<AsesoresDTO> genericResponse = new GenericResponse<AsesoresDTO>();
    	
        try {
            
            genericResponse.setLista(asesoresService.getDataAsesores());
			genericResponse.setCodigo(String.valueOf(HttpStatus.OK.value()));
			genericResponse.setMensaje(HttpStatus.OK.getReasonPhrase());
			
			return new ResponseEntity<GenericResponse<AsesoresDTO>>(genericResponse, HttpStatus.OK);
        } catch (Exception e) {
        	e.printStackTrace();
        	log.error(e.getMessage(), e);
			genericResponse.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
			genericResponse.setMensaje(e.getMessage());
			return new ResponseEntity<GenericResponse<AsesoresDTO>>(genericResponse, HttpStatus.OK);
		}
    }

    @GetMapping(value = "/getAsesores/{asesId}")
    public ResponseEntity<GenericResponse<AsesoresDTO>> getAsesores(@PathVariable("asesId")
    Long asesId) throws Exception {
    	
    	GenericResponse<AsesoresDTO> genericResponse = new GenericResponse<AsesoresDTO>();
    	
        try {
            Asesores asesores = asesoresService.getAsesores(asesId);

            genericResponse.setLista(new ArrayList<AsesoresDTO>(Arrays.asList(asesoresMapper.asesoresToAsesoresDTO(asesores))));
			genericResponse.setCodigo(String.valueOf(HttpStatus.OK.value()));
			genericResponse.setMensaje(HttpStatus.OK.getReasonPhrase());
			
			return new ResponseEntity<GenericResponse<AsesoresDTO>>(genericResponse, HttpStatus.OK);
        } catch (Exception e) {
        	e.printStackTrace();
        	log.error(e.getMessage(), e);
			genericResponse.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
			genericResponse.setMensaje(e.getMessage());
			return new ResponseEntity<GenericResponse<AsesoresDTO>>(genericResponse, HttpStatus.OK);
		}
    }
}
