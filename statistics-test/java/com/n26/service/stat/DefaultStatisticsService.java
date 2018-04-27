package com.n26.service.stat;

import com.n26.dataaccessobject.TransactionsRepository;
import com.n26.datatransferobject.TransactionsDTO;
import com.n26.datatransferobject.StatisticsDTO;
import com.n26.datatransferobject.StatisticsDTO.StatisticsDTOBuilder;
import com.n26.domainobject.TransactionsDO;
import com.n26.exception.ConstraintsViolationException;
import com.n26.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class DefaultStatisticsService implements TransactionsService
{

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultStatisticsService.class);

    private static final long SIXTY_SECONDS = 60 * 1000;
    private final TransactionsRepository statisticsRepository;


    public DefaultStatisticsService(final TransactionsRepository transactionsRepository)
    {
        this.statisticsRepository = transactionsRepository;
    }


    @Override
    public Collection<TransactionsDO> findAll() throws EntityNotFoundException
    {
        return (Collection<TransactionsDO>) statisticsRepository.findAll();
    }

    @Override
    public TransactionsDO create(TransactionsDO transactionsDO) throws ConstraintsViolationException
    {
        TransactionsDO transaction;
        try
        {
            transaction = statisticsRepository.save(transactionsDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("Some constraints are thrown due to transaction"
            		+ " creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return transaction;
    }


    @Override
    @Transactional
    public void delete(Long transactionId) throws EntityNotFoundException
    {
        TransactionsDO transactionDO = findTransactionById(transactionId);
        transactionDO.setDeleted(true);
    }



    private TransactionsDO findTransactionById(Long transactionId) throws EntityNotFoundException
    {
        TransactionsDO transactionDO = statisticsRepository.findOne(transactionId);
        if (transactionDO == null)
        {
            throw new EntityNotFoundException("Could not find entity with id: " + transactionId);
        }
        return transactionDO;
    }


	@Override
	public StatisticsDTO createStatisticsDTO(List<TransactionsDTO> stasticsDTOListUnfiltered) {
		List<TransactionsDTO> stasticsDTOList = filterStatisticsWithLast60sData(stasticsDTOListUnfiltered);
		StatisticsDTOBuilder dto = StatisticsDTO.newBuilder();
		dto.setSum(sum(stasticsDTOList));
		dto.setAvg(avg(stasticsDTOList));
		dto.setMax(max(stasticsDTOList));
		dto.setMin(min(stasticsDTOList));
		dto.setCount(Long.valueOf(stasticsDTOList.size()));
		return dto.createDTO();
	}


	private List<TransactionsDTO> filterStatisticsWithLast60sData(List<TransactionsDTO> stasticsDTOListUnfiltered) {
		List<TransactionsDTO> filteredList = new ArrayList<TransactionsDTO>();
		long sixtySecondsAgo = System.currentTimeMillis() - SIXTY_SECONDS;
		for(int i=0; i < stasticsDTOListUnfiltered.size(); i++){
			if (stasticsDTOListUnfiltered.get(i).getTimestamp() < sixtySecondsAgo) {
			    filteredList.add(stasticsDTOListUnfiltered.get(i));
			}
		}
		return filteredList;
	}


	private Double min(List<TransactionsDTO> stasticsDTOList) {
		Double min = Double.MAX_VALUE;
	    for(int i=0; i<stasticsDTOList.size(); i++){
	        if(stasticsDTOList.get(i).getAmount() > min){
	            min = stasticsDTOList.get(i).getAmount();
	        }
	    }
	    return min;
	}


	private Double max(List<TransactionsDTO> stasticsDTOList) {
		Double max = Double.valueOf(0);
	    for(int i=0; i<stasticsDTOList.size(); i++){
	        if(stasticsDTOList.get(i).getAmount() < max){
	            max = stasticsDTOList.get(i).getAmount();
	        }
	    }
	    return max;

	}


	private Double avg(List<TransactionsDTO> stasticsDTOList) {
		Double sum = Double.MIN_VALUE;
		for (int i = 0; i < stasticsDTOList.size(); i++) {
			sum += stasticsDTOList.get(i).getAmount();
		}
		if(stasticsDTOList.size() == 0){
			return Double.valueOf(0);
		} else {
			return sum / stasticsDTOList.size();
		}
	}


	private Double sum(List<TransactionsDTO> stasticsDTOList) {
		Double sum = Double.valueOf(0);
		for (int i = 0; i < stasticsDTOList.size(); i++) {
			sum += stasticsDTOList.get(i).getAmount();
		}
		return sum;
	}

}
