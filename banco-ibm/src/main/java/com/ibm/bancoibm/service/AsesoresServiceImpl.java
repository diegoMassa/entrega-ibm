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

import com.ibm.bancoibm.domain.Asesores;
import com.ibm.bancoibm.dto.AsesoresDTO;
import com.ibm.bancoibm.exception.ZMessManager;
import com.ibm.bancoibm.mapper.AsesoresMapper;
import com.ibm.bancoibm.repository.AsesoresRepository;
import com.ibm.bancoibm.utility.Utilities;


@Scope("singleton")
@Service
public class AsesoresServiceImpl implements AsesoresService {
    private static final Logger log = LoggerFactory.getLogger(AsesoresServiceImpl.class);

    @Autowired
    private AsesoresRepository asesoresRepository;
    @Autowired
    private AsesoresMapper asesoresMapper;
    @Autowired
    private Validator validator;

    public void validateAsesores(Asesores asesores) throws Exception {
        try {
            Set<ConstraintViolation<Asesores>> constraintViolations = validator.validate(asesores);

            if (constraintViolations.size() > 0) {
                StringBuilder strMessage = new StringBuilder();

                for (ConstraintViolation<Asesores> constraintViolation : constraintViolations) {
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
    public List<Asesores> getAsesores() throws Exception {
        log.debug("finding all Asesores instances");

        List<Asesores> list = new ArrayList<Asesores>();

        try {
            list = asesoresRepository.findAll();
        } catch (Exception e) {
            log.error("finding all Asesores failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "Asesores");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveAsesores(Asesores entity) throws Exception {
        log.debug("saving Asesores instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Asesores");
            }

            validateAsesores(entity);

            if (entity.getAsesId() != null && getAsesores(entity.getAsesId()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            asesoresRepository.save(entity);
            log.debug("save Asesores successful");
        } catch (Exception e) {
            log.error("save Asesores failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteAsesores(Asesores entity) throws Exception {
        log.debug("deleting Asesores instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Asesores");
        }

        if (entity.getAsesId() == null) {
            throw new ZMessManager().new EmptyFieldException("asesId");
        }

        try {
            asesoresRepository.deleteById(entity.getAsesId());
            log.debug("delete Asesores successful");
        } catch (Exception e) {
            log.error("delete Asesores failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateAsesores(Asesores entity) throws Exception {
        log.debug("updating Asesores instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Asesores");
            }

            validateAsesores(entity);

            asesoresRepository.update(entity);

            log.debug("update Asesores successful");
        } catch (Exception e) {
            log.error("update Asesores failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<AsesoresDTO> getDataAsesores() throws Exception {
        try {
            List<Asesores> asesores = asesoresRepository.findAll();

            List<AsesoresDTO> asesoresDTO = new ArrayList<AsesoresDTO>();

            for (Asesores asesoresTmp : asesores) {
                AsesoresDTO asesoresDTO2 = asesoresMapper.asesoresToAsesoresDTO(asesoresTmp);
                asesoresDTO.add(asesoresDTO2);
            }

            return asesoresDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Asesores getAsesores(Long asesId) throws Exception {
        log.debug("getting Asesores instance");

        Asesores entity = null;

        try {
            if (asesoresRepository.findById(asesId).isPresent()) {
                entity = asesoresRepository.findById(asesId).get();
            }
        } catch (Exception e) {
            log.error("get Asesores failed", e);
            throw new ZMessManager().new FindingException("Asesores");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Asesores> findPageAsesores(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<Asesores> entity = null;

        try {
            entity = asesoresRepository.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Asesores Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberAsesores() throws Exception {
        Long entity = null;

        try {
            entity = asesoresRepository.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Asesores Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Asesores> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<Asesores> list = new ArrayList<Asesores>();
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
            list = asesoresRepository.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
