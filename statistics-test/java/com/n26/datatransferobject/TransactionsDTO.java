package com.n26.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionsDTO
{
    @JsonIgnore
    private Long id;

    @NotNull(message = "Amount can not be null!")
    private Double amount;

    private Long timestamp;


    private TransactionsDTO()
    {
    }


    private TransactionsDTO(Long id, Double amount, Long timestamp)
    {
        this.id = id;
        this.amount = amount;
        this.timestamp = timestamp;
    }


    public static TransactionsDTOBuilder newBuilder()
    {
        return new TransactionsDTOBuilder();
    }


    @JsonProperty
    public Long getId()
    {
        return id;
    }


    public Double getAmount()
    {
        return amount;
    }


    public Long getTimestamp()
    {
        return timestamp;
    }

    public static class TransactionsDTOBuilder
    {
        private Long id;
        private Double amount;
        private Long timestamp;


        public TransactionsDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }


        public TransactionsDTOBuilder setAmount(Double amount)
        {
            this.amount = amount;
            return this;
        }


        public TransactionsDTOBuilder setTimestamp(Long timestamp)
        {
            this.timestamp = timestamp;
            return this;
        }


        public TransactionsDTO createTransactionDTO()
        {
            return new TransactionsDTO(id, amount, timestamp);
        }

    }
}
