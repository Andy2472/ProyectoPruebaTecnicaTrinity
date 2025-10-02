package com.yarl.pruebatecnica.pruebatecnica.application.services;

import com.yarl.pruebatecnica.pruebatecnica.application.ports.input.TransactionServicePort;
import com.yarl.pruebatecnica.pruebatecnica.application.ports.output.AccountPersistencePort;
import com.yarl.pruebatecnica.pruebatecnica.application.ports.output.TransactionPersistencePort;
import com.yarl.pruebatecnica.pruebatecnica.domain.enums.TipoTransacciones;
import com.yarl.pruebatecnica.pruebatecnica.domain.model.Account;
import com.yarl.pruebatecnica.pruebatecnica.domain.model.Transaction;
import com.yarl.pruebatecnica.pruebatecnica.domain.exceptions.AccountNotFoundException;
import com.yarl.pruebatecnica.pruebatecnica.domain.exceptions.InsufficientBalanceException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionService implements TransactionServicePort {

    private final TransactionPersistencePort transactionPersistencePort;
    private final AccountPersistencePort accountPersistencePort;

    public TransactionService(TransactionPersistencePort transactionPersistencePort,
                              AccountPersistencePort accountPersistencePort) {
        this.transactionPersistencePort = transactionPersistencePort;
        this.accountPersistencePort = accountPersistencePort;
    }

    @Override
    public Transaction createConsignacion(Long targetAccountId, BigDecimal amount) {
        Account targetAccount = accountPersistencePort.getAccountById(targetAccountId)
                .orElseThrow(AccountNotFoundException::new);

        targetAccount.setSaldo(targetAccount.getSaldo().add(amount));
        accountPersistencePort.save(targetAccountId, targetAccount);

        Transaction transaction = new Transaction(
                null,
                TipoTransacciones.CONSIGNACIONES,
                null,
                targetAccount,
                amount,
                LocalDateTime.now()
        );

        return transactionPersistencePort.save(transaction);
    }

    @Override
    public Transaction createRetiro(Long sourceAccountId, BigDecimal amount) {
        Account sourceAccount = accountPersistencePort.getAccountById(sourceAccountId)
                .orElseThrow(AccountNotFoundException::new);

        if (sourceAccount.getSaldo().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Saldo insuficiente");
        }

        sourceAccount.setSaldo(sourceAccount.getSaldo().subtract(amount));
        accountPersistencePort.save(sourceAccountId, sourceAccount);

        Transaction transaction = new Transaction(
                null,
                TipoTransacciones.RETIRO,
                sourceAccount,
                null,
                amount,
                LocalDateTime.now()
        );

        return transactionPersistencePort.save(transaction);
    }

    @Override
    public Transaction createTransferencia(Long sourceAccountId, Long targetAccountId, BigDecimal amount) {
        Account sourceAccount = accountPersistencePort.getAccountById(sourceAccountId)
                .orElseThrow(AccountNotFoundException::new);
        Account targetAccount = accountPersistencePort.getAccountById(targetAccountId)
                .orElseThrow(AccountNotFoundException::new);

        if (sourceAccount.getSaldo().compareTo(amount) < 0) {
            throw new Exception("Saldo insuficiente");
        }

        sourceAccount.setSaldo(sourceAccount.getSaldo().subtract(amount));
        targetAccount.setSaldo(targetAccount.getSaldo().add(amount));

        accountPersistencePort.save(sourceAccount);
        accountPersistencePort.save(targetAccount);

        Transaction transaction = new Transaction(
                null,
                TipoTransacciones.TRANSFERENCIA,
                sourceAccount,
                targetAccount,
                amount,
                LocalDateTime.now()
        );

        return transactionPersistencePort.save(transaction);
    }
}
