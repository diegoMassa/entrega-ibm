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
import com.ibm.bancoibm.domain.Asesores;
import com.ibm.bancoibm.service.AsesoresService;

@Rollback(true)
@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest
@AutoConfigureTestDatabase
public class AsesoresServiceImplTest {
	
	private static final Logger log = LoggerFactory.getLogger(AsesoresServiceImplTest.class);
	@Autowired
	private AsesoresService asesoresService;

	@Test
	public void testValidateAsesores() {
		try {
			log.info("### Inicia test: testValidateAsesores()   ###");
			Asesores asesor = new Asesores();
			asesor.setNombre("Pepito Perez");
			asesor.setEspecialidad("Venta de Inmuebles");
			
			asesoresService.validateAsesores(asesor);
			log.info("### Finaliza test: testValidateAsesores()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

	@Test
	public void testGetAsesores() {
		try {
			log.info("### Inicia test: testGetAsesores()   ###");
			asesoresService.getAsesores().forEach(asesor -> log.info(new Gson().toJson(asesor)));
			log.info("### Finaliza test: testGetAsesores()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

	@Test
	public void testSaveAsesores() {
		try {
			log.info("### Inicia test: testSaveAsesores()   ###");
			Asesores asesor = new Asesores();
			asesor.setNombre("Pepito Perez");
			asesor.setEspecialidad("Venta de Inmuebles");
			
			asesoresService.saveAsesores(asesor);
			log.info("### Finaliza test: testSaveAsesores()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

	@Test
	public void testDeleteAsesores() {
		try {
			log.info("### Inicia test: testDeleteAsesores()   ###");
			Asesores asesor = new Asesores();
			asesor = asesoresService.getAsesores().get(0);
			
			asesoresService.deleteAsesores(asesor);
			log.info("### Finaliza test: testDeleteAsesores()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

	@Test
	public void testUpdateAsesores() {
		try {
			log.info("### Inicia test: testUpdateAsesores()   ###");
			Asesores asesor = new Asesores();
			asesor = asesoresService.getAsesores().get(0);
			asesor.setNombre(asesor.getNombre()+" Actualizado");
			
			asesoresService.updateAsesores(asesor);
			log.info("### Finaliza test: testUpdateAsesores()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

	@Test
	public void testGetDataAsesores() {
		try {
			log.info("### Inicia test: testGetDataAsesores()   ###");
			asesoresService.getDataAsesores().forEach(asesor -> log.info(new Gson().toJson(asesor)));
			log.info("### Finaliza test: testGetDataAsesores()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

	@Test
	public void testGetAsesoresLong() {
		try {
			log.info("### Inicia test: testGetDataAsesores()   ###");
			log.info(new Gson().toJson(asesoresService.getAsesores(1L)));
			log.info("### Finaliza test: testGetDataAsesores()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

	@Test
	public void testFindPageAsesores() {
		try {
			log.info("### Inicia test: testFindPageAsesores()   ###");
			asesoresService.findPageAsesores("nombre", true, 0, 10).forEach(asesor -> log.info(new Gson().toJson(asesor)));
			log.info("### Finaliza test: testFindPageAsesores()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

	@Test
	public void testFindTotalNumberAsesores() {
		try {
			log.info("### Inicia test: testFindTotalNumberAsesores()   ###");
			log.info("Numero total de asesores: "+asesoresService.findTotalNumberAsesores());
			log.info("### Finaliza test: testFindTotalNumberAsesores()   ###");
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
			Object[] variables = {"nombre", true, "%CARLOS%", " LIKE "};
			log.info(new Gson().toJson(asesoresService.findByCriteria(variables, null, null)));
			log.info("### Finaliza test: testFindByCriteria()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

}
