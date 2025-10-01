package com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.output.persistence;

import com.yarl.pruebatecnica.pruebatecnica.application.ports.output.ClientPersistencePort;
import com.yarl.pruebatecnica.pruebatecnica.domain.model.Client;
import com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.output.persistence.mapper.ClientPersistenceMapper;
import com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.output.persistence.repository.JpaClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClientPersistenceAdapter implements ClientPersistencePort {

    private final JpaClienteRepository jpaClienteRepository;

    private final ClientPersistenceMapper clientPersistenceMapper;

    @Override
    public Optional<Client> getClientById(Long idClient) {

        /* En el caso en el que exista el Cliente, pues lo va a "convertir-traducir"
        en un Cliente, no en un ClientEntity, ya que un ClienEntity es lo que estamos
         recibiendo*/

        return jpaClienteRepository.findById(idClient)
                .map(clientPersistenceMapper::toClient);
    }

    @Override
    public List<Client> findAll() {
        /* Este metodo "toClientList" es el que declaramos en el archivo
        ClientPersistenceMapper.java, que lo que hace es traducir lo que
         llega en la lista, a Client */
        return clientPersistenceMapper.toClientList(jpaClienteRepository.findAll());
    }

    @Override
    public Client save(Client cliente) {
        /* Esta línea lo que hace es:
         "Convierte cliente → entidad JPA → guarda en BD → convierte entidad guardada → cliente de dominio → devuelve." */
        return clientPersistenceMapper.toClient(jpaClienteRepository.save(clientPersistenceMapper.toClientEntity(cliente)));
    }

    @Override
    public void deleteById(Long idCliente) {
        jpaClienteRepository.deleteById(idCliente);
    }

    /* El adaptador de salida, es el que va a implementar el puerto de salida. */



}
