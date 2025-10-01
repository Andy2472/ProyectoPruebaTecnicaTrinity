package com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.input.restController.mapper;

import com.yarl.pruebatecnica.pruebatecnica.domain.model.Client;
import com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.input.restController.model.request.ClientCreateRequest;
import com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.input.restController.model.response.ClientResponse;
import com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.output.persistence.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/* Esto se usa para que se pueda inyectar como un componente de Spring */
@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ClientRestMapper {
    /* Como el Cliente que se envía, no va a tener un id, pues entonces este se ignora
    y se pondría el valor null */
    Client toClient(ClientCreateRequest clientCreateRequest);

    ClientResponse toClientResponse(Client client);

    List<ClientResponse> toClientResponseList(List<Client> client);

}
