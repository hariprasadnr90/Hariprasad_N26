package com.n26.service.stat;

import com.n26.datatransferobject.TransactionsDTO;
import com.n26.datatransferobject.StatisticsDTO;
import com.n26.domainobject.TransactionsDO;
import com.n26.exception.ConstraintsViolationException;
import com.n26.exception.EntityNotFoundException;

import java.util.Collection;
import java.util.List;

public interface TransactionsService
{

	Collection<TransactionsDO> findAll() throws EntityNotFoundException;

    TransactionsDO create(TransactionsDO transactionDO) throws ConstraintsViolationException;

    void delete(Long transactionId) throws EntityNotFoundException;

	StatisticsDTO createStatisticsDTO(List<TransactionsDTO> stasticsDTOList);

}
