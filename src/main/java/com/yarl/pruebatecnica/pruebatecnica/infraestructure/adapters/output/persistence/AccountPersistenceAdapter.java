package com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.output.persistence;

import com.yarl.pruebatecnica.pruebatecnica.application.ports.output.AccountPersistencePort;
import com.yarl.pruebatecnica.pruebatecnica.domain.model.Account;
import com.yarl.pruebatecnica.pruebatecnica.domain.model.Client;
import com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.output.persistence.entity.AccountEntity;
import com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.output.persistence.entity.ClientEntity;
import com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.output.persistence.mapper.AccountPersistenceMapper;
import com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.output.persistence.mapper.ClientPersistenceMapper;
import com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.output.persistence.repository.JpaAccountRepository;
import com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.output.persistence.repository.JpaClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AccountPersistenceAdapter implements AccountPersistencePort {

    private final JpaClienteRepository jpaClienteRepository;
    private final ClientPersistenceMapper clientPersistenceMapper;
    private final JpaAccountRepository jpaAccountRepository;
    private final AccountPersistenceMapper accountPersistenceMapper;

    @Override
    public Optional<Account> getAccountById(Long idAccount) {
        return jpaAccountRepository.findById(idAccount)
                .map(accountPersistenceMapper::toAccount);
    }

    @Override
    public List<Account> getAccountsByClientId(Long idClient) {
        return jpaAccountRepository.findByClientEntity_Id(idClient)
                .stream()
                .map(accountPersistenceMapper::toAccount)
                .collect(Collectors.toList());
    }


    @Override
    public Account save(Long idClient, Account account) {
        // 1) Buscar cliente (entidad)
        ClientEntity clientEntity = jpaClienteRepository.findById(idClient)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id " + idClient));

        // 2) Mapear modelo dominio -> entidad
        System.out.println(">>> Dominio recibido: " + account.getTipoProducto());
        AccountEntity accountEntity = accountPersistenceMapper.toAccountEntity(account);
        System.out.println(">>> Entidad mapeada: " + accountEntity.getTipoCuenta());


        // 3) Asignar relación con cliente
        accountEntity.setClientEntity(clientEntity);

        // 4) Guardar entidad
        AccountEntity saved = jpaAccountRepository.save(accountEntity);

        // 5) Mapear entidad guardada -> modelo dominio y devolverlo
        return accountPersistenceMapper.toAccount(saved);
    }

    @Override
    public void deleteById(Long idAccount) {
        jpaAccountRepository.deleteById(idAccount);
    }

    @Override
    public Client RelatedClientToAccount(Long idAccount) {
        // Traemos la cuenta
        AccountEntity accountEntity = jpaAccountRepository.findById(idAccount)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada con id " + idAccount));

        // Obtenemos el cliente asociado
        ClientEntity clientEntity = accountEntity.getClientEntity();

        // Convertimos con el mapper (para no devolver la entidad)
        return clientPersistenceMapper.toClient(clientEntity);
    }

}
