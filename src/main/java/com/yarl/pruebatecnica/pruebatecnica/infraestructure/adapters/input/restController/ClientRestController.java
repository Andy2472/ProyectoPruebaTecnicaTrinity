package com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.input.restController;

import com.yarl.pruebatecnica.pruebatecnica.application.ports.input.ClientServicePort;
import com.yarl.pruebatecnica.pruebatecnica.domain.model.Client;
import com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.input.restController.mapper.ClientRestMapper;
import com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.input.restController.model.request.ClientCreateRequest;
import com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.input.restController.model.response.ClientResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClientRestController {

    private final ClientServicePort clientServicePort;
    private final ClientRestMapper clientRestMapper;

    public ClientRestController(ClientServicePort clientServicePort, ClientRestMapper clientRestMapper) {
        this.clientServicePort = clientServicePort;
        this.clientRestMapper = clientRestMapper;
    }


    @GetMapping
    public List<ClientResponse> listarClientes(){
        return clientRestMapper.toClientResponseList(clientServicePort.getAllClients());
    }

    @GetMapping("{idCliente}")
    public ClientResponse encontrarClientePorId(@PathVariable Long idCliente){
        return clientRestMapper.toClientResponse(clientServicePort.getClientById(idCliente));
    }

    @PostMapping("/crear")
    public ResponseEntity<ClientResponse> save(@Valid @RequestBody ClientCreateRequest clientCreateRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clientRestMapper.toClientResponse(
                        clientServicePort.createClient(
                                clientRestMapper.toClient(clientCreateRequest))));
    }

    @PutMapping("/actualizar/{idCliente}")
    public ClientResponse actualizar(@PathVariable Long idCliente, @Valid @RequestBody ClientCreateRequest clientResponse){
        return clientRestMapper.toClientResponse(
                clientServicePort.updateClients(
                        idCliente, clientRestMapper.toClient(clientResponse)));
    }

    @DeleteMapping("/eliminar/{idCliente}")
    public void delete(@PathVariable Long idCliente){
        clientServicePort.deleteClient(idCliente);
    }
}
