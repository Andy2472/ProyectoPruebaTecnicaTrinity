package com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.output.persistence.mapper;

import com.yarl.pruebatecnica.pruebatecnica.domain.model.Account;
import com.yarl.pruebatecnica.pruebatecnica.domain.model.Client;
import com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.output.persistence.entity.AccountEntity;
import com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.output.persistence.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountPersistenceMapper {

    @Mapping(source = "tipoProducto", target = "tipoCuenta")
    AccountEntity toAccountEntity(Account account);

    @Mapping(source = "tipoCuenta", target = "tipoProducto")
    Account toAccount(AccountEntity accountEntity);

    List<Account> toAccountList(List<AccountEntity> accountEntities);
}


