package com.ibm.bancoibm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.bancoibm.dto.AuthDTO;
import com.ibm.bancoibm.dto.GenericResponse;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuhtenticationRestController {
    
	private static final Logger log = LoggerFactory.getLogger(AuhtenticationRestController.class);

    @PostMapping(value = "/login")
	public ResponseEntity<GenericResponse<AuthDTO>> login( @RequestBody AuthDTO parametros) throws Exception {
		
		log.info(" ### Peticion entrante para **/auth/login [POST] ... ### ");
		
		GenericResponse<AuthDTO> genericResponse = new GenericResponse<AuthDTO>();
		
		try {
			if(parametros == null || parametros.getUser() == null || parametros.getPassword() == null
					|| parametros.getPassword().trim().equals("") || parametros.getUser().trim().equals("")) {
				throw new Exception("Usuario y clave incorrectos");
			}
			
			if(!parametros.getUser().trim().equalsIgnoreCase("admin") || !parametros.getPassword().trim().equalsIgnoreCase("admin")) {
				throw new Exception("Usuario y clave incorrectos");
			}
			
			genericResponse.setLista(null);
			genericResponse.setCodigo(String.valueOf(HttpStatus.OK.value()));
			genericResponse.setMensaje(HttpStatus.OK.getReasonPhrase());
			log.info(" ### Finalizando peticion entrante con respuesta: "+genericResponse.toString()+" ... ### ");
			return new ResponseEntity<GenericResponse<AuthDTO>>(genericResponse, HttpStatus.OK);
		} catch (Exception e) {
			log.error(" ### Ocurrio un error en el servicio **/auth/login [POST], retornando un BAD_REQUEST (400) como codigo de respuesta ... ### ", e);
			genericResponse.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
			genericResponse.setMensaje(e.getMessage());
			return new ResponseEntity<GenericResponse<AuthDTO>>(genericResponse, HttpStatus.OK);
		}
	}
    
}
