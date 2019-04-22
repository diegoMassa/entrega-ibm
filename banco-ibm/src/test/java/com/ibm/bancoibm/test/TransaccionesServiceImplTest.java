package com.ibm.bancoibm.test;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
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
import com.ibm.bancoibm.domain.Tarjetas;
import com.ibm.bancoibm.domain.Transacciones;
import com.ibm.bancoibm.mapper.TransaccionesMapper;
import com.ibm.bancoibm.service.TarjetasService;
import com.ibm.bancoibm.service.TransaccionesService;

@Rollback(true)
@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest
@AutoConfigureTestDatabase
public class TransaccionesServiceImplTest {
	
	private static final Logger log = LoggerFactory.getLogger(TransaccionesServiceImplTest.class);
	@Autowired
	private TarjetasService tarjetaService;
	@Autowired
	private TransaccionesService transaccionesService;
	@Autowired
	private TransaccionesMapper transaccionesMapper;
	private Gson formatter = new Gson();
	private Tarjetas tarjetaTransaccion;
	
	@BeforeEach
    public void testInit() {
        try {
        	tarjetaTransaccion = tarjetaService.getTarjetas(1L);
        } catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
    }

	@Test
	public void testValidateTransacciones() {
		try {
			log.info("### Inicia test: testValidateTransacciones()   ###");
			Transacciones transaccion = new Transacciones();
			transaccion.setTarjetas(tarjetaTransaccion);
			transaccion.setFecha(new Date());
			transaccion.setMonto(10000L);
			transaccion.setDescripcion("JUnit Jupiter Test");
			transaccionesService.validateTransacciones(transaccion);
			log.info("### Finaliza test: testValidateTransacciones()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

	@Test
	public void testGetTransacciones() {
		try {
			log.info("### Inicia test: testGetTransacciones()   ###");
			transaccionesService.getTransacciones().forEach(transaccion -> {
				try {
					log.info(formatter.toJson(transaccionesMapper.transaccionesToTransaccionesDTO(transaccion)));
				} catch (Exception e) {
					log.error(e.getMessage(), e);
					StringWriter errors = new StringWriter();
					e.printStackTrace(new PrintWriter(errors));
					fail(errors.toString());
				}
			});
			log.info("### Finaliza test: testGetTransacciones()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

	@Test
	public void testSaveTransacciones() {
		try {
			log.info("### Inicia test: testSaveTransacciones()   ###");
			Transacciones transaccion = new Transacciones();
			transaccion.setTarjetas(tarjetaTransaccion);
			transaccion.setFecha(new Date());
			transaccion.setMonto(10000L);
			transaccion.setDescripcion("JUnit Jupiter Test");
			
			transaccionesService.saveTransacciones(transaccion);
			log.info("### Finaliza test: testSaveTransacciones()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

	@Test
	public void testDeleteTransacciones() {
		try {
			log.info("### Inicia test: testDeleteTransacciones()   ###");
			Transacciones transaccion = new Transacciones();
			Object[] variables = {"tarjetas.tarjeId", false, 1L, "="};
			transaccion = transaccionesService.findByCriteria(variables, null, null).get(0);
			
			transaccionesService.deleteTransacciones(transaccion);
			log.info("### Finaliza test: testDeleteTransacciones()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

	@Test
	public void testUpdateTransacciones() {
		try {
			log.info("### Inicia test: testUpdateTransacciones()   ###");
			Transacciones transaccion = new Transacciones();
			transaccion = transaccionesService.getTransacciones().get(0);
			transaccion.setDescripcion(transaccion.getDescripcion() + " Actualizado");
			
			transaccionesService.updateTransacciones(transaccion);
			log.info("### Finaliza test: testUpdateTransacciones()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

	@Test
	public void testGetDataTransacciones() {
		try {
			log.info("### Inicia test: testGetDataTransacciones()   ###");
			transaccionesService.getDataTransacciones().forEach(transaccion -> log.info(formatter.toJson(transaccion)));
			log.info("### Finaliza test: testGetDataTransacciones()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

	@Test
	public void testGetTransaccionesLong() {
		try {
			log.info("### Inicia test: testGetTransaccionesLong()   ###");
			log.info(formatter.toJson(transaccionesMapper.transaccionesToTransaccionesDTO(transaccionesService.getTransacciones(1L))));
			log.info("### Finaliza test: testGetTransaccionesLong()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

	@Test
	public void testFindPageTransacciones() {
		try {
			log.info("### Inicia test: testFindPageTransacciones()   ###");
			transaccionesService.findPageTransacciones("tranId", true, 0, 10).forEach(transaccion -> {
				try {
					log.info(formatter.toJson(transaccionesMapper.transaccionesToTransaccionesDTO(transaccion)));
				} catch (Exception e) {
					log.error(e.getMessage(), e);
					StringWriter errors = new StringWriter();
					e.printStackTrace(new PrintWriter(errors));
					fail(errors.toString());
				}
			});
			log.info("### Finaliza test: testFindPageTransacciones()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

	@Test
	public void testFindTotalNumberTransacciones() {
		try {
			log.info("### Inicia test: testFindTotalNumberTransacciones()   ###");
			log.info("Numero total de transacciones: "+transaccionesService.findTotalNumberTransacciones());
			log.info("### Finaliza test: testFindTotalNumberTransacciones()   ###");
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
			Object[] variables = {"tarjetas.tarjeId", false, 1L, "="};
			log.info(formatter.toJson(transaccionesMapper.listTransaccionesToListTransaccionesDTO(transaccionesService.findByCriteria(variables, null, null))));
			log.info("### Finaliza test: testFindByCriteria()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

}
