package com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.input.restController;

import com.yarl.pruebatecnica.pruebatecnica.application.ports.input.AccountServicePort;
import com.yarl.pruebatecnica.pruebatecnica.domain.model.Account;
import com.yarl.pruebatecnica.pruebatecnica.domain.model.Client;
import com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.input.restController.mapper.AccountRestMapper;
import com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.input.restController.mapper.ClientRestMapper;
import com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.input.restController.model.request.AccountCreateRequest;
import com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.input.restController.model.response.AccountResponse;
import com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.input.restController.model.response.ClientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class AccountRestController {
    private final AccountServicePort accountServicePort;
    private final AccountRestMapper accountRestMapper;
    private final ClientRestMapper clientRestMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountRestController.class);

    public AccountRestController(AccountServicePort accountServicePort, AccountRestMapper accountRestMapper, ClientRestMapper clientRestMapper) {
        this.accountServicePort = accountServicePort;
        this.accountRestMapper = accountRestMapper;
        this.clientRestMapper = clientRestMapper;
    }

    @GetMapping("/{idCuenta}")
    public AccountResponse accountById(@PathVariable Long idCuenta) {
        return accountRestMapper.toAccountResponse(accountServicePort.getAccountById(idCuenta));
    }

    /* Con este Endpoint estoy trayendo las cuentas asociadas a un cliente */
    @GetMapping("/cliente/{idCliente}")
    public List<AccountResponse> accountsByClientId(@PathVariable Long idCliente) {
        return accountServicePort.getAccountsByClientId(idCliente)
                .stream()
                .map(accountRestMapper::toAccountResponse)
                .toList();
    }
    /* Con este Endpoint estoy trayendo el cliente asociado a la cuenta */
    @GetMapping("/{idCuenta}/cliente")
    public ResponseEntity<ClientResponse> relatedClient(@PathVariable Long idCuenta) {
        Client client = accountServicePort.RelatedClientToAccount(idCuenta);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clientRestMapper.toClientResponse(client));
    }

    // -------------------- POST --------------------
    @PostMapping("/cliente/{idCliente}")
    public ResponseEntity<AccountResponse> createAccount(
            @PathVariable Long idCliente,
            @RequestBody AccountCreateRequest request) {

        System.out.println(">>> JSON recibido: " + request.getTipoProducto());

        Account account = accountRestMapper.toAccount(request);
        System.out.println(">>> Dominio mapeado: " + account.getTipoProducto());

        Account saved = accountServicePort.crearCuenta(idCliente, account);
        return ResponseEntity.ok(accountRestMapper.toAccountResponse(saved));
    }

}
