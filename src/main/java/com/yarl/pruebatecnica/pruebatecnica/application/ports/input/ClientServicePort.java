package com.yarl.pruebatecnica.pruebatecnica.application.ports.input;

import com.yarl.pruebatecnica.pruebatecnica.domain.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientServicePort {

    /* Aqui si se definen todo el tema de los casos de uso, es por eso
    que tambien se define el metodo update */

    Client createClient(Client clientes);

    Client getClientById(Long idClient);

    List<Client> getAllClients();

    Client updateClients(Long idCliente, Client Clientes);

    void deleteClient(Long idClient);
}
