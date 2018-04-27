package com.n26.dataaccessobject;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.n26.domainobject.TransactionsDO;

public interface TransactionsRepository extends CrudRepository<TransactionsDO, Long>
{

    
}
