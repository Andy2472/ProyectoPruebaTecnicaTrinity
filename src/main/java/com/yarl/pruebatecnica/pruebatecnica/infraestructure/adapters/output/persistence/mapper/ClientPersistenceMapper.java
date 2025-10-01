package com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.output.persistence.mapper;

import com.yarl.pruebatecnica.pruebatecnica.domain.model.Client;
import com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.output.persistence.entity.ClientEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientPersistenceMapper {

    /* Aquí recibimos un tipo que es Client, que hace referencia al modelo
    Client de la capa "domain", y por supuesto, entonces devolvemos ahora
     de tipo ClientEntity */
    ClientEntity toClientEntity(Client client);

    Client toClient(ClientEntity clientEntity);

    List<Client> toClientList(List<ClientEntity> clientEntities);

}
