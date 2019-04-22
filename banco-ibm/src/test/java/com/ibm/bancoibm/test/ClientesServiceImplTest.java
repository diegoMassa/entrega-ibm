package com.ibm.bancoibm.test;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.ibm.bancoibm.domain.Clientes;
import com.ibm.bancoibm.mapper.ClientesMapper;
import com.ibm.bancoibm.service.ClientesService;

@Rollback(true)
@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest
@AutoConfigureTestDatabase
public class ClientesServiceImplTest {
	
	private static final Logger log = LoggerFactory.getLogger(ClientesServiceImplTest.class);
	@Autowired
	private ClientesService clientesService;
	@Autowired
	private ClientesMapper clientesMapper;
	private Gson formatter = new Gson();

	@Test
	public void testValidateClientes() {
		try {
			log.info("### Inicia test: testValidateClientes()   ###");
			Clientes cliente = new Clientes();
			cliente.setNombre("Pepito Perez");
			cliente.setDireccion("Calle 1 # 2 - 3");
			cliente.setTelefono(1234567L);
			cliente.setCiudad("Cali");
			clientesService.validateClientes(cliente);
			log.info("### Finaliza test: testValidateClientes()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

	@Test
	public void testGetClientes() {
		try {
			log.info("### Inicia test: testGetClientes()   ###");
			clientesService.getClientes().forEach(cliente -> {
				try {
					log.info(formatter.toJson(clientesMapper.clientesToClientesDTO(cliente)));
				} catch (Exception e) {
					log.error(e.getMessage(), e);
					StringWriter errors = new StringWriter();
					e.printStackTrace(new PrintWriter(errors));
					fail(errors.toString());
				}
			});
			log.info("### Finaliza test: testGetClientes()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

	@Test
	public void testSaveClientes() {
		try {
			log.info("### Inicia test: testSaveClientes()   ###");
			Clientes cliente = new Clientes();
			cliente.setNombre("Pepito Perez");
			cliente.setDireccion("Calle 1 # 2 - 3");
			cliente.setTelefono(1234567L);
			cliente.setCiudad("Cali");
			
			clientesService.saveClientes(cliente);
			log.info("### Finaliza test: testSaveClientes()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

	@Test
	public void testDeleteClientes() {
		try {
			log.info("### Inicia test: testDeleteClientes()   ###");
			Clientes cliente = new Clientes();
			Object[] variables = {"nombre", true, "%Luz%", " LIKE "};
			cliente = clientesService.findByCriteria(variables, null, null).get(0);
			
			clientesService.deleteClientes(cliente);
			log.info("### Finaliza test: testDeleteClientes()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

	@Test
	public void testUpdateClientes() {
		try {
			log.info("### Inicia test: testUpdateClientes()   ###");
			Clientes cliente = new Clientes();
			cliente = clientesService.getClientes().get(0);
			cliente.setNombre(cliente.getNombre()+" Actualizado");
			
			clientesService.updateClientes(cliente);
			log.info("### Finaliza test: testUpdateClientes()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

	@Test
	public void testGetDataClientes() {
		try {
			log.info("### Inicia test: testGetDataClientes()   ###");
			clientesService.getDataClientes().forEach(cliente -> log.info(formatter.toJson(cliente)));
			log.info("### Finaliza test: testGetDataClientes()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

	@Test
	public void testGetClientesLong() {
		try {
			log.info("### Inicia test: testGetClientesLong()   ###");
			log.info(formatter.toJson(clientesMapper.clientesToClientesDTO(clientesService.getClientes(1L))));
			log.info("### Finaliza test: testGetClientesLong()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

	@Test
	public void testFindPageClientes() {
		try {
			log.info("### Inicia test: testFindPageClientes()   ###");
			clientesService.findPageClientes("nombre", true, 0, 10).forEach(cliente -> {
				try {
					log.info(formatter.toJson(clientesMapper.clientesToClientesDTO(cliente)));
				} catch (Exception e) {
					log.error(e.getMessage(), e);
					StringWriter errors = new StringWriter();
					e.printStackTrace(new PrintWriter(errors));
					fail(errors.toString());
				}
			});
			log.info("### Finaliza test: testFindPageClientes()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

	@Test
	public void testFindTotalNumberClientes() {
		try {
			log.info("### Inicia test: testFindTotalNumberClientes()   ###");
			log.info("Numero total de clientes: "+clientesService.findTotalNumberClientes());
			log.info("### Finaliza test: testFindTotalNumberClientes()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

	@Test
	public void testFindByCriteria() {
		try {
			log.info("### Inicia test: testFindByCriteria()   ###");
			Object[] variables = {"nombre", true, "%Diego%", " LIKE "};
			log.info(formatter.toJson(clientesMapper.listClientesToListClientesDTO(clientesService.findByCriteria(variables, null, null))));
			log.info("### Finaliza test: testFindByCriteria()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

}
