package com.yarl.pruebatecnica.pruebatecnica.application.ports.input;

import com.yarl.pruebatecnica.pruebatecnica.domain.model.Account;

public interface AccountServicePort {
    Account crearCuenta(Long idCliente, Account account);

    Account getAccountById(Long idAccount);

    Account getAccountByClientId(Long idCliente);

    Account updateAccount(Long idAccount, Account account);

    void eliminarAccount(Long idAccount);
}
