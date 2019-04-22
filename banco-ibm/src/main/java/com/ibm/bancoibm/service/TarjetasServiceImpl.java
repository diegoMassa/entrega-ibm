package com.ibm.bancoibm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.bancoibm.domain.Tarjetas;
import com.ibm.bancoibm.domain.Transacciones;
import com.ibm.bancoibm.dto.TarjetasDTO;
import com.ibm.bancoibm.exception.ZMessManager;
import com.ibm.bancoibm.mapper.TarjetasMapper;
import com.ibm.bancoibm.repository.TarjetasRepository;
import com.ibm.bancoibm.repository.TransaccionesRepository;
import com.ibm.bancoibm.utility.Utilities;


@Scope("singleton")
@Service
public class TarjetasServiceImpl implements TarjetasService {
    private static final Logger log = LoggerFactory.getLogger(TarjetasServiceImpl.class);

    @Autowired
    private TarjetasRepository tarjetasRepository;
    
    @Autowired
    private TarjetasMapper tarjetasMapper;
   
    @Autowired
    private Validator validator;

    @Autowired
    private TransaccionesRepository transaccionesRepository;

    @Autowired
    ClientesService serviceClientes1;

    public void validateTarjetas(Tarjetas tarjetas) throws Exception {
        try {
            Set<ConstraintViolation<Tarjetas>> constraintViolations = validator.validate(tarjetas);

            if (constraintViolations.size() > 0) {
                StringBuilder strMessage = new StringBuilder();

                for (ConstraintViolation<Tarjetas> constraintViolation : constraintViolations) {
                    strMessage.append(constraintViolation.getPropertyPath()
                                                         .toString());
                    strMessage.append(" - ");
                    strMessage.append(constraintViolation.getMessage());
                    strMessage.append(". \n");
                }

                throw new Exception(strMessage.toString());
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<Tarjetas> getTarjetas() throws Exception {
        log.debug("finding all Tarjetas instances");

        List<Tarjetas> list = new ArrayList<Tarjetas>();

        try {
            list = tarjetasRepository.findAll();
        } catch (Exception e) {
            log.error("finding all Tarjetas failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "Tarjetas");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveTarjetas(Tarjetas entity) throws Exception {
        log.debug("saving Tarjetas instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Tarjetas");
            }

            validateTarjetas(entity);

            if (entity.getTarjeId() != null && getTarjetas(entity.getTarjeId()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            tarjetasRepository.save(entity);
            log.debug("save Tarjetas successful");
        } catch (Exception e) {
            log.error("save Tarjetas failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteTarjetas(Tarjetas entity) throws Exception {
        log.debug("deleting Tarjetas instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Tarjetas");
        }

        if (entity.getTarjeId() == null) {
            throw new ZMessManager().new EmptyFieldException("tarjeId");
        }

        List<Transacciones> transaccioneses = null;
        try {
            transaccioneses = transaccionesRepository.findByProperty("tarjetas.tarjeId", entity.getTarjeId());

            if (Utilities.validationsList(transaccioneses) == true) {
                throw new ZMessManager().new DeletingException("Consumos");
            }
            tarjetasRepository.deleteById(entity.getTarjeId());
            log.debug("delete Tarjetas successful");
        } catch (Exception e) {
            log.error("delete Tarjetas failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateTarjetas(Tarjetas entity) throws Exception {
        log.debug("updating Tarjetas instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Tarjetas");
            }

            validateTarjetas(entity);

            tarjetasRepository.update(entity);

            log.debug("update Tarjetas successful");
        } catch (Exception e) {
            log.error("update Tarjetas failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<TarjetasDTO> getDataTarjetas() throws Exception {
        try {
            List<Tarjetas> tarjetas = tarjetasRepository.findAll();

            List<TarjetasDTO> tarjetasDTO = new ArrayList<TarjetasDTO>();

            for (Tarjetas tarjetasTmp : tarjetas) {
                TarjetasDTO tarjetasDTO2 = tarjetasMapper.tarjetasToTarjetasDTO(tarjetasTmp);
                tarjetasDTO.add(tarjetasDTO2);
            }

            return tarjetasDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Tarjetas getTarjetas(Long tarjeId) throws Exception {
        log.debug("getting Tarjetas instance");

        Tarjetas entity = null;

        try {
            if (tarjetasRepository.findById(tarjeId).isPresent()) {
                entity = tarjetasRepository.findById(tarjeId).get();
            }
        } catch (Exception e) {
            log.error("get Tarjetas failed", e);
            throw new ZMessManager().new FindingException("Tarjetas");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Tarjetas> findPageTarjetas(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<Tarjetas> entity = null;

        try {
            entity = tarjetasRepository.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Tarjetas Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberTarjetas() throws Exception {
        Long entity = null;

        try {
            entity = tarjetasRepository.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Tarjetas Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Tarjetas> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<Tarjetas> list = new ArrayList<Tarjetas>();
        String where = new String();
        String tempWhere = new String();

        if (variables != null) {
            for (int i = 0; i < variables.length; i++) {
                if ((variables[i] != null) && (variables[i + 1] != null) &&
                        (variables[i + 2] != null) &&
                        (variables[i + 3] != null)) {
                    String variable = (String) variables[i];
                    Boolean booVariable = (Boolean) variables[i + 1];
                    Object value = variables[i + 2];
                    String comparator = (String) variables[i + 3];

                    if (booVariable.booleanValue()) {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " \'" +
                            value + "\' )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " \'" + value + "\' )");
                    } else {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " " +
                            value + " )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " " + value + " )");
                    }
                }

                i = i + 3;
            }
        }

        if (variablesBetween != null) {
            for (int j = 0; j < variablesBetween.length; j++) {
                if ((variablesBetween[j] != null) &&
                        (variablesBetween[j + 1] != null) &&
                        (variablesBetween[j + 2] != null) &&
                        (variablesBetween[j + 3] != null) &&
                        (variablesBetween[j + 4] != null)) {
                    String variable = (String) variablesBetween[j];
                    Object value = variablesBetween[j + 1];
                    Object value2 = variablesBetween[j + 2];
                    String comparator1 = (String) variablesBetween[j + 3];
                    String comparator2 = (String) variablesBetween[j + 4];
                    tempWhere = (tempWhere.length() == 0)
                        ? ("(" + value + " " + comparator1 + " " + variable +
                        " and " + variable + " " + comparator2 + " " + value2 +
                        " )")
                        : (tempWhere + " AND (" + value + " " + comparator1 +
                        " " + variable + " and " + variable + " " +
                        comparator2 + " " + value2 + " )");
                }

                j = j + 4;
            }
        }

        if (variablesBetweenDates != null) {
            for (int k = 0; k < variablesBetweenDates.length; k++) {
                if ((variablesBetweenDates[k] != null) &&
                        (variablesBetweenDates[k + 1] != null) &&
                        (variablesBetweenDates[k + 2] != null)) {
                    String variable = (String) variablesBetweenDates[k];
                    Object object1 = variablesBetweenDates[k + 1];
                    Object object2 = variablesBetweenDates[k + 2];
                    String value = null;
                    String value2 = null;

                    try {
                        Date date1 = (Date) object1;
                        Date date2 = (Date) object2;
                        value = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date1);
                        value2 = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date2);
                    } catch (Exception e) {
                        list = null;
                        throw e;
                    }

                    tempWhere = (tempWhere.length() == 0)
                        ? ("(model." + variable + " between " + value +
                        " and " + value2 + ")")
                        : (tempWhere + " AND (model." + variable + " between " +
                        value + " and " + value2 + ")");
                }

                k = k + 2;
            }
        }

        if (tempWhere.length() == 0) {
            where = null;
        } else {
            where = "(" + tempWhere + ")";
        }

        try {
            list = tarjetasRepository.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }

	@Override
	@Transactional(readOnly = true)
	public List<Tarjetas> getTarjetasByClieId(Long clieId) throws Exception {
		List<Tarjetas> listTarjetasDeCliente = null;
		try {
			listTarjetasDeCliente = tarjetasRepository.findByProperty("clientes.clieId", clieId);
		} catch (Exception e) {
			   throw e;
		}
		return listTarjetasDeCliente;
	}
}