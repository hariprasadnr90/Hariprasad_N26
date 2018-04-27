package com.n26.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.n26.controller.mapper.StatisticsMapper;
import com.n26.datatransferobject.TransactionsDTO;
import com.n26.datatransferobject.StatisticsDTO;
import com.n26.domainobject.TransactionsDO;
import com.n26.exception.ConstraintsViolationException;
import com.n26.exception.EntityNotFoundException;
import com.n26.service.stat.TransactionsService;

@RestController
@RequestMapping("v1")
public class StatisticsController
{

    private final TransactionsService statisticsService;


    @Autowired
    public StatisticsController(final TransactionsService statisticsService)
    {
        this.statisticsService = statisticsService;
    }


    @GetMapping("/transactions")
    public List<TransactionsDTO> getTransactions() throws EntityNotFoundException
    {
        return StatisticsMapper.makeTransactionsDTOList(statisticsService.findAll());
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionsDTO createData(@Valid @RequestBody TransactionsDTO dataDTO) throws ConstraintsViolationException
    {
        TransactionsDO dataDO = StatisticsMapper.makeDO(dataDTO);
        return StatisticsMapper.makeDTO(statisticsService.create(dataDO));
    }

    @GetMapping("/statistics")
    public StatisticsDTO getStatistics() throws EntityNotFoundException
    {
    	List<TransactionsDTO> stasticsDTOList = StatisticsMapper.makeTransactionsDTOList(statisticsService.findAll());
        return StatisticsMapper.makeStatisticsDTOList(statisticsService.createStatisticsDTO(stasticsDTOList));
    }

}
