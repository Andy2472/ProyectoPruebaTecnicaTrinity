package com.yarl.pruebatecnica.pruebatecnica.application.ports.output;

import com.yarl.pruebatecnica.pruebatecnica.domain.model.Account;
import com.yarl.pruebatecnica.pruebatecnica.domain.model.Client;

import java.util.List;
import java.util.Optional;

public interface AccountPersistencePort {
    Optional<Account> getAccountById(Long idAccount);
    List<Account> getAccountsByClientId(Long idClient);
    Account save(Long idClient, Account account);
    void deleteById(Long idAccount);

    Client RelatedClientToAccount(Long idAccount);
}
