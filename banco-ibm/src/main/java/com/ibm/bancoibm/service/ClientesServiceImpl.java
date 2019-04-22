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

import com.ibm.bancoibm.domain.Clientes;
import com.ibm.bancoibm.domain.Tarjetas;
import com.ibm.bancoibm.dto.ClientesDTO;
import com.ibm.bancoibm.exception.ZMessManager;
import com.ibm.bancoibm.mapper.ClientesMapper;
import com.ibm.bancoibm.repository.ClientesRepository;
import com.ibm.bancoibm.repository.TarjetasRepository;
import com.ibm.bancoibm.utility.Utilities;


@Scope("singleton")
@Service
public class ClientesServiceImpl implements ClientesService {
    private static final Logger log = LoggerFactory.getLogger(ClientesServiceImpl.class);

    @Autowired
    private ClientesRepository clientesRepository;
    
    @Autowired
    private ClientesMapper clientesMapper;
    
    @Autowired
    private Validator validator;

    @Autowired
    private TarjetasRepository tarjetasRepository;

    public void validateClientes(Clientes clientes) throws Exception {
        try {
            Set<ConstraintViolation<Clientes>> constraintViolations = validator.validate(clientes);

            if (constraintViolations.size() > 0) {
                StringBuilder strMessage = new StringBuilder();

                for (ConstraintViolation<Clientes> constraintViolation : constraintViolations) {
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
    public List<Clientes> getClientes() throws Exception {
        log.debug("finding all Clientes instances");

        List<Clientes> list = new ArrayList<Clientes>();

        try {
            list = clientesRepository.findAll();
        } catch (Exception e) {
            log.error("finding all Clientes failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "Clientes");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveClientes(Clientes entity) throws Exception {
        log.debug("saving Clientes instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Clientes");
            }

            validateClientes(entity);

            if (entity.getClieId() != null && getClientes(entity.getClieId()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            clientesRepository.save(entity);
            log.debug("save Clientes successful");
        } catch (Exception e) {
            log.error("save Clientes failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteClientes(Clientes entity) throws Exception {
        log.debug("deleting Clientes instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Clientes");
        }

        if (entity.getClieId() == null) {
            throw new ZMessManager().new EmptyFieldException("clieId");
        }

        List<Tarjetas> tarjetases = null;

        try {
            tarjetases = tarjetasRepository.findByProperty("clientes.clieId", entity.getClieId());

            if (Utilities.validationsList(tarjetases) == true) {
                throw new ZMessManager().new DeletingException("tarjetas");
            }

            clientesRepository.deleteById(entity.getClieId());
            log.debug("delete Clientes successful");
        } catch (Exception e) {
            log.error("delete Clientes failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateClientes(Clientes entity) throws Exception {
        log.debug("updating Clientes instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Clientes");
            }

            validateClientes(entity);

            clientesRepository.update(entity);

            log.debug("update Clientes successful");
        } catch (Exception e) {
            log.error("update Clientes failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<ClientesDTO> getDataClientes() throws Exception {
        try {
            List<Clientes> clientes = clientesRepository.findAll();

            List<ClientesDTO> clientesDTO = new ArrayList<ClientesDTO>();

            for (Clientes clientesTmp : clientes) {
                ClientesDTO clientesDTO2 = clientesMapper.clientesToClientesDTO(clientesTmp);
                clientesDTO.add(clientesDTO2);
            }

            return clientesDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Clientes getClientes(Long clieId) throws Exception {
        log.debug("getting Clientes instance");

        Clientes entity = null;

        try {
            if (clientesRepository.findById(clieId).isPresent()) {
                entity = clientesRepository.findById(clieId).get();
            }
        } catch (Exception e) {
            log.error("get Clientes failed", e);
            throw new ZMessManager().new FindingException("Clientes");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Clientes> findPageClientes(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<Clientes> entity = null;

        try {
            entity = clientesRepository.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Clientes Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberClientes() throws Exception {
        Long entity = null;

        try {
            entity = clientesRepository.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Clientes Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Clientes> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<Clientes> list = new ArrayList<Clientes>();
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
            list = clientesRepository.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}