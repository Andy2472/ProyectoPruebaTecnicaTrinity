package com.yarl.pruebatecnica.pruebatecnica.application.ports.output;

import com.yarl.pruebatecnica.pruebatecnica.domain.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientPersistencePort {

    /* Aquí no se define el metodo update, ya que esto es el puerto de salida,
    no el puerto de entrada donde se definen los casos de uso, es por eso que el
    metodo update, se define como si fuera de guardado, ya que dependiendo de si
    es null inserta o actualiza */

    /* Cuando nos referimos a persistencia, en software significa guardar datos
     de forma permanente en un sistema de almacenamiento.
     Lo contrario es que los datos vivan solo en memoria (RAM) y se pierdan
     cuando la app se apague. */

    Optional<Client> getClientById(Long idClient);

    List<Client> findAll();

    Client save(Client cliente);

    void deleteById(Long idCliente);
}
