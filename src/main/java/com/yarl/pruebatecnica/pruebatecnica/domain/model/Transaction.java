package com.yarl.pruebatecnica.pruebatecnica.domain.model;

import com.yarl.pruebatecnica.pruebatecnica.domain.enums.TipoTransacciones;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {

    public enum TransactionType {
        CONSIGNACION, RETIRO, TRANSFERENCIA
    }

    private Long id;
    private TipoTransacciones type;
    private Account sourceAccount; // cuenta origen (null si es consignación)
    private Account targetAccount; // cuenta destino (null si es retiro)
    private BigDecimal amount;
    private LocalDateTime date;

    public Transaction(Long id, TipoTransacciones type, Account sourceAccount, Account targetAccount, BigDecimal amount, LocalDateTime date) {
        this.id = id;
        this.type = type;
        this.sourceAccount = sourceAccount;
        this.targetAccount = targetAccount;
        this.amount = amount;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    /*public void setId(Long id) {
        this.id = id;
    }*/

    public TipoTransacciones getType() {
        return type;
    }

    public void setType(TipoTransacciones type) {
        this.type = type;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public Account getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(Account targetAccount) {
        this.targetAccount = targetAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
