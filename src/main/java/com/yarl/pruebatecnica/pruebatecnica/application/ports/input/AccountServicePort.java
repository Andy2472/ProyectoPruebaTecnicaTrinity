package com.yarl.pruebatecnica.pruebatecnica.application.ports.input;

import com.yarl.pruebatecnica.pruebatecnica.domain.model.Account;
import com.yarl.pruebatecnica.pruebatecnica.domain.model.Client;

import java.util.List;

public interface AccountServicePort {
    Account crearCuenta(Long idCliente, Account account);

    Account getAccountById(Long idAccount);

    List<Account> getAccountsByClientId(Long idClient);


    Account updateAccount(Long idAccount, Account account);

    void eliminarAccount(Long idAccount);

    Client RelatedClientToAccount(Long idAccount);
}
