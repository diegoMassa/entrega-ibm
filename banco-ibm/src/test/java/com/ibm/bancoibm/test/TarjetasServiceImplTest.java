package com.ibm.bancoibm.test;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.MethodSorters;
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
import com.ibm.bancoibm.domain.Tarjetas;
import com.ibm.bancoibm.mapper.TarjetasMapper;
import com.ibm.bancoibm.service.ClientesService;
import com.ibm.bancoibm.service.TarjetasService;

@Rollback(true)
@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest
@AutoConfigureTestDatabase
public class TarjetasServiceImplTest {
	
	private static final Logger log = LoggerFactory.getLogger(TarjetasServiceImplTest.class);
	@Autowired
	private TarjetasService tarjetasService;
	@Autowired
	private ClientesService clienteService;
	@Autowired
	private TarjetasMapper tarjetasMapper;
	private Gson formatter = new Gson();
	private Clientes clienteTarjeta;
	
	@BeforeEach
    public void testInit() {
        try {
        	clienteTarjeta = clienteService.getClientes(1L);
        } catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
    }

	@Test
	public void testValidateTarjetas() {
		try {
			log.info("### Inicia test: testValidateTarjetas()   ###");
			Tarjetas tarjeta = new Tarjetas();
			tarjeta.setClientes(clienteTarjeta);
			tarjeta.setCcv(123L);
			tarjeta.setNumeroTarjeta(1234987619287645L);
			tarjeta.setTipoTarjeta("MASTERCARD");
			tarjetasService.validateTarjetas(tarjeta);
			log.info("### Finaliza test: testValidateTarjetas()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

	@Test
	public void testGetTarjetas() {
		try {
			log.info("### Inicia test: testGetTarjetas()   ###");
			tarjetasService.getTarjetas().forEach(tarjeta -> {
				try {
					log.info(formatter.toJson(tarjetasMapper.tarjetasToTarjetasDTO(tarjeta)));
				} catch (Exception e) {
					log.error(e.getMessage(), e);
					StringWriter errors = new StringWriter();
					e.printStackTrace(new PrintWriter(errors));
					fail(errors.toString());
				}
			});
			log.info("### Finaliza test: testGetTarjetas()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

	@Test
	public void testSaveTarjetas() {
		try {
			log.info("### Inicia test: testSaveTarjetas()   ###");
			Tarjetas tarjeta = new Tarjetas();
			tarjeta.setClientes(clienteTarjeta);
			tarjeta.setCcv(123L);
			tarjeta.setNumeroTarjeta(1234987619287645L);
			tarjeta.setTipoTarjeta("MASTERCARD");
			
			tarjetasService.saveTarjetas(tarjeta);
			log.info("### Finaliza test: testSaveTarjetas()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

	@Test
	public void testDeleteTarjetas() {
		try {
			log.info("### Inicia test: testDeleteTarjetas()   ###");
			Tarjetas tarjeta = new Tarjetas();
			Object[] variables = {"ccv", false, 888L, "="};
			tarjeta = tarjetasService.findByCriteria(variables, null, null).get(0);
			
			tarjetasService.deleteTarjetas(tarjeta);
			log.info("### Finaliza test: testDeleteTarjetas()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

	@Test
	public void testUpdateTarjetas() {
		try {
			log.info("### Inicia test: testUpdateTarjetas()   ###");
			Tarjetas tarjeta = new Tarjetas();
			tarjeta = tarjetasService.getTarjetas().get(0);
			tarjeta.setCcv(999L);
			
			tarjetasService.updateTarjetas(tarjeta);
			log.info("### Finaliza test: testUpdateTarjetas()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

	@Test
	public void testGetDataTarjetas() {
		try {
			log.info("### Inicia test: testGetDataTarjetas()   ###");
			tarjetasService.getDataTarjetas().forEach(tarjeta -> log.info(formatter.toJson(tarjeta)));
			log.info("### Finaliza test: testGetDataTarjetas()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

	@Test
	public void testGetTarjetasLong() {
		try {
			log.info("### Inicia test: testGetTarjetasLong()   ###");
			log.info(formatter.toJson(tarjetasMapper.tarjetasToTarjetasDTO(tarjetasService.getTarjetas(1L))));
			log.info("### Finaliza test: testGetTarjetasLong()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

	@Test
	public void testFindPageTarjetas() {
		try {
			log.info("### Inicia test: testFindPageTarjetas()   ###");
			tarjetasService.findPageTarjetas("ccv", true, 0, 10).forEach(tarjeta -> {
				try {
					log.info(formatter.toJson(tarjetasMapper.tarjetasToTarjetasDTO(tarjeta)));
				} catch (Exception e) {
					log.error(e.getMessage(), e);
					StringWriter errors = new StringWriter();
					e.printStackTrace(new PrintWriter(errors));
					fail(errors.toString());
				}
			});
			log.info("### Finaliza test: testFindPageTarjetas()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

	@Test
	public void testFindTotalNumberTarjetas() {
		try {
			log.info("### Inicia test: testFindTotalNumberTarjetas()   ###");
			log.info("Numero total de tarjetas: "+tarjetasService.findTotalNumberTarjetas());
			log.info("### Finaliza test: testFindTotalNumberTarjetas()   ###");
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
			Object[] variables = {"tipoTarjeta", true, "%VISA%", " LIKE "};
			log.info(formatter.toJson(tarjetasMapper.listTarjetasToListTarjetasDTO(tarjetasService.findByCriteria(variables, null, null))));
			log.info("### Finaliza test: testFindByCriteria()   ###");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail(errors.toString());
		}
	}

}
