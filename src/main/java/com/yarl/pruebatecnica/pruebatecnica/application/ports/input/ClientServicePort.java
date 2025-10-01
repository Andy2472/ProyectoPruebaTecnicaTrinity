package com.yarl.pruebatecnica.pruebatecnica.application.ports.input;

import com.yarl.pruebatecnica.pruebatecnica.domain.model.Account;
import com.yarl.pruebatecnica.pruebatecnica.domain.model.Client;

import java.util.List;

public interface ClientServicePort {

    /* Aqui si se definen todo el tema de los casos de uso, es por eso
    que tambien se define el metodo update */

    Client createClient(Client clientes);

    Client getClientById(Long idClient);

    List<Client> getAllClients();

    Client updateClients(Long idCliente, Client Clientes);

    void deleteClient(Long idClient);

    /* Aqui no se podría poner el llamar una lista de clientes, por el tema de responsabilidad únic, porque a la final al hacer una inyeccion de dependencias,
    estariamos dandole una responsabilidad de clientes, a alguien que solamente debería tener responsabilidad de clientes */
}
