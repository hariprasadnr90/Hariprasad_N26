package com.n26.domainobject;


import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(
    name = "transactions")
public class TransactionsDO
{

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column(nullable = false)
    @NotNull(message = "Amount can not be null!")
    private Double amount;

    @Column(nullable = false)
    private Long timestamp;

    @Column(nullable = false)
    private Boolean deleted = false;

    private TransactionsDO()
    {
    }


    public TransactionsDO(Double amount, Long timestamp)
    {
        this.amount = amount;
        this.timestamp = timestamp;
        this.deleted = false;
    }


    public Long getId()
    {
        return id;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public Double getAmount()
    {
        return amount;
    }


    public Long getTimestamp()
    {
        return timestamp;
    }


    public Boolean getDeleted()
    {
        return deleted;
    }


    public void setDeleted(Boolean deleted)
    {
        this.deleted = deleted;
    }

}
