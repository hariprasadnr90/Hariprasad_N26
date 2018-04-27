package com.n26.controller.mapper;

import com.n26.datatransferobject.TransactionsDTO;
import com.n26.datatransferobject.StatisticsDTO;
import com.n26.domainobject.TransactionsDO;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class StatisticsMapper
{
    public static TransactionsDO makeDO(TransactionsDTO transactionDTO)
    {
        return new TransactionsDO(transactionDTO.getAmount(), transactionDTO.getTimestamp());
    }


    public static TransactionsDTO makeDTO(TransactionsDO statisticsDO)
    {
        TransactionsDTO.TransactionsDTOBuilder transactionsDTOBuilder = TransactionsDTO.newBuilder()
            .setId(statisticsDO.getId())
            .setAmount(statisticsDO.getAmount())
            .setTimestamp(statisticsDO.getTimestamp());

        return transactionsDTOBuilder.createTransactionDTO();
    }

    public static StatisticsDTO makeDTO2(StatisticsDTO statisticsDTO)
    {
        StatisticsDTO.StatisticsDTOBuilder statisticsDTOBuilder = StatisticsDTO.newBuilder()
            .setSum(statisticsDTO.getSum())
            .setAvg(statisticsDTO.getAvg())
            .setMin(statisticsDTO.getMin())
            .setMax(statisticsDTO.getMax())
            .setCount(statisticsDTO.getCount());

        return statisticsDTOBuilder.createDTO();
    }

    public static List<TransactionsDTO> makeTransactionsDTOList(Collection<TransactionsDO> transactions)
    {
        return transactions.stream()
            .map(StatisticsMapper::makeDTO)
            .collect(Collectors.toList());
    }
    
    public static StatisticsDTO makeStatisticsDTOList(StatisticsDTO statisticsDTO)
    {
        return statisticsDTO;
    }
}
