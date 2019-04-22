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

import com.ibm.bancoibm.domain.Transacciones;
import com.ibm.bancoibm.dto.TransaccionesDTO;
import com.ibm.bancoibm.exception.ZMessManager;
import com.ibm.bancoibm.mapper.TransaccionesMapper;
import com.ibm.bancoibm.repository.TransaccionesRepository;
import com.ibm.bancoibm.utility.Utilities;


@Scope("singleton")
@Service
public class TransaccionesServiceImpl implements TransaccionesService {
    private static final Logger log = LoggerFactory.getLogger(TransaccionesServiceImpl.class);

    @Autowired
    private TransaccionesRepository transaccionesRepository;
    
    @Autowired
    private TransaccionesMapper transaccionesMapper;
   
    @Autowired
    private Validator validator;

    @Autowired
    TarjetasService serviceTarjetas1;

    public void validateTransacciones(Transacciones transacciones)
        throws Exception {
        try {
            Set<ConstraintViolation<Transacciones>> constraintViolations = validator.validate(transacciones);

            if (constraintViolations.size() > 0) {
                StringBuilder strMessage = new StringBuilder();

                for (ConstraintViolation<Transacciones> constraintViolation : constraintViolations) {
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
    public List<Transacciones> getTransacciones() throws Exception {
        log.debug("finding all Transacciones instances");

        List<Transacciones> list = new ArrayList<Transacciones>();

        try {
            list = transaccionesRepository.findAll();
        } catch (Exception e) {
            log.error("finding all Transacciones failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "Transacciones");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveTransacciones(Transacciones entity)
        throws Exception {
        log.debug("saving Transacciones instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "Transacciones");
            }

            validateTransacciones(entity);

            if (entity.getTranId() != null && getTransacciones(entity.getTranId()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            transaccionesRepository.save(entity);
            log.debug("save Transacciones successful");
        } catch (Exception e) {
            log.error("save Transacciones failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteTransacciones(Transacciones entity)
        throws Exception {
        log.debug("deleting Transacciones instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Transacciones");
        }

        if (entity.getTranId() == null) {
            throw new ZMessManager().new EmptyFieldException("tranId");
        }

        try {
            transaccionesRepository.deleteById(entity.getTranId());
            log.debug("delete Transacciones successful");
        } catch (Exception e) {
            log.error("delete Transacciones failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateTransacciones(Transacciones entity)
        throws Exception {
        log.debug("updating Transacciones instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "Transacciones");
            }

            validateTransacciones(entity);

            transaccionesRepository.update(entity);

            log.debug("update Transacciones successful");
        } catch (Exception e) {
            log.error("update Transacciones failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<TransaccionesDTO> getDataTransacciones()
        throws Exception {
        try {
            List<Transacciones> transacciones = transaccionesRepository.findAll();

            List<TransaccionesDTO> transaccionesDTO = new ArrayList<TransaccionesDTO>();

            for (Transacciones transaccionesTmp : transacciones) {
                TransaccionesDTO transaccionesDTO2 = transaccionesMapper.transaccionesToTransaccionesDTO(transaccionesTmp);
                transaccionesDTO.add(transaccionesDTO2);
            }

            return transaccionesDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Transacciones getTransacciones(Long tranId)
        throws Exception {
        log.debug("getting Transacciones instance");

        Transacciones entity = null;

        try {
            if (transaccionesRepository.findById(tranId).isPresent()) {
                entity = transaccionesRepository.findById(tranId).get();
            }
        } catch (Exception e) {
            log.error("get Transacciones failed", e);
            throw new ZMessManager().new FindingException("Transacciones");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Transacciones> findPageTransacciones(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<Transacciones> entity = null;

        try {
            entity = transaccionesRepository.findPage(sortColumnName,
                    sortAscending, startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Transacciones Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberTransacciones() throws Exception {
        Long entity = null;

        try {
            entity = transaccionesRepository.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Transacciones Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Transacciones> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<Transacciones> list = new ArrayList<Transacciones>();
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
            list = transaccionesRepository.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }

	@Override
	@Transactional(readOnly = true)
	public List<Transacciones> getTransaccionesByTarjId(Long tarjId) throws Exception {
		List<Transacciones> listTransaccionesDeTarjeta = null;
		try {
			listTransaccionesDeTarjeta = transaccionesRepository.findByProperty("tarjetas.tarjeId", tarjId);
		} catch (Exception e) {
			   throw e;
		}
		return listTransaccionesDeTarjeta;
	}
}
