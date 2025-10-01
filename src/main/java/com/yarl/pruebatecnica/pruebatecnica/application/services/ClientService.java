package com.yarl.pruebatecnica.pruebatecnica.application.services;

import com.yarl.pruebatecnica.pruebatecnica.application.ports.input.ClientServicePort;
import com.yarl.pruebatecnica.pruebatecnica.application.ports.output.ClientPersistencePort;
import com.yarl.pruebatecnica.pruebatecnica.domain.Exceptions.ClientNotFoundException;
import com.yarl.pruebatecnica.pruebatecnica.domain.model.Client;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ClientService implements ClientServicePort {

    /* Aquí, en el servicio, se hace uso de la implementacion del puerto de entrada,
     osea, de los métodos declarados en la interfaz y se usan los metodos
     del puerto de salida */

    private final ClientPersistencePort clientPersistencePort;

    public ClientService(ClientPersistencePort clientPersistencePort){
        this.clientPersistencePort = clientPersistencePort;
    }

    @Override
    public Client createClient(Client clientes) {
        return clientPersistencePort.save(clientes);
    }

    @Override
    public Client getClientById(Long idClient) {
        return clientPersistencePort.getClientById(idClient).orElseThrow(ClientNotFoundException::new);
    }

    @Override
    public List<Client> getAllClients() {
        return clientPersistencePort != null ? clientPersistencePort.findAll() : null;
    }

    @Override
    public Client updateClients(Long idCliente, Client Clientes) {

        /* Buscamos el cliente por id, si existe, modificamos los campos que
        queremos modificar, sino existe, se ejecuta la excepcion */

        return clientPersistencePort.getClientById(idCliente)
                .map(savedStudent -> {
                    savedStudent.setTipoIdentificacion(Clientes.getTipoIdentificacion());
                    savedStudent.setNumeroIdentificacion(Clientes.getNumeroIdentificacion());
                    savedStudent.setNombres(Clientes.getNombres());
                    savedStudent.setApellidos(Clientes.getApellidos());
                    savedStudent.setCorreo(Clientes.getCorreo());
                    savedStudent.setFechaNacimiento(Clientes.getFechaNacimiento());
                    savedStudent.setFechaCreacion(Clientes.getFechaCreacion());
                    savedStudent.setFechaModificacion(Clientes.getFechaModificacion());
                    return clientPersistencePort.save(savedStudent);
                })
                .orElseThrow(ClientNotFoundException::new);
    }

    @Override
    public void deleteClient(Long idClient) {
        if(clientPersistencePort.getClientById(idClient).isEmpty()){
            throw new ClientNotFoundException();
        }

        clientPersistencePort.deleteById(idClient);
    }


}
