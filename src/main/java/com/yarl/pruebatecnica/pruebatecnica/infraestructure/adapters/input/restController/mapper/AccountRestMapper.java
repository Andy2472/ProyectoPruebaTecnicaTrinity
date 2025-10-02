package com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.input.restController.mapper;

import com.yarl.pruebatecnica.pruebatecnica.domain.model.Account;
import com.yarl.pruebatecnica.pruebatecnica.domain.model.Client;
import com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.input.restController.model.request.AccountCreateRequest;
import com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.input.restController.model.request.ClientCreateRequest;
import com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.input.restController.model.response.AccountResponse;
import com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.input.restController.model.response.ClientResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AccountRestMapper {

    Account toAccount(AccountCreateRequest accountCreateRequest);

    AccountResponse toAccountResponse(Account account);

    List<AccountResponse> toAccountResponseList(List<Account> account);

}
