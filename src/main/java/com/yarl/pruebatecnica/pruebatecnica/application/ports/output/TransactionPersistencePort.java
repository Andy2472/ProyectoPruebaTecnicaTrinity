package com.yarl.pruebatecnica.pruebatecnica.application.ports.output;

import com.yarl.pruebatecnica.pruebatecnica.domain.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionPersistencePort {

    Transaction save(Transaction transaction);

    List<Transaction> findAll();

    Optional<Transaction> findById(Long id);
}
