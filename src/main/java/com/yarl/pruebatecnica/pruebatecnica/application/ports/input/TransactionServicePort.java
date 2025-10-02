package com.yarl.pruebatecnica.pruebatecnica.application.ports.input;

import com.yarl.pruebatecnica.pruebatecnica.domain.model.Transaction;

import java.math.BigDecimal;

public interface TransactionServicePort {

    Transaction createConsignacion(Long targetAccountId, BigDecimal amount);

    Transaction createRetiro(Long sourceAccountId, BigDecimal amount);

    Transaction createTransferencia(Long sourceAccountId, Long targetAccountId, BigDecimal amount);
}
