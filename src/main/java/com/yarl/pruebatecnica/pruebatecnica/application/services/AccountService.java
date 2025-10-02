package com.yarl.pruebatecnica.pruebatecnica.application.services;

import com.yarl.pruebatecnica.pruebatecnica.application.ports.input.AccountServicePort;
import com.yarl.pruebatecnica.pruebatecnica.application.ports.input.ClientServicePort;
import com.yarl.pruebatecnica.pruebatecnica.application.ports.output.AccountPersistencePort;
import com.yarl.pruebatecnica.pruebatecnica.application.ports.output.ClientPersistencePort;
import com.yarl.pruebatecnica.pruebatecnica.domain.enums.EstadoCuenta;
import com.yarl.pruebatecnica.pruebatecnica.domain.enums.TipoProducto;
import com.yarl.pruebatecnica.pruebatecnica.domain.exceptions.AccountNotFoundException;
import com.yarl.pruebatecnica.pruebatecnica.domain.exceptions.ClientNotFoundException;
import com.yarl.pruebatecnica.pruebatecnica.domain.model.Account;
import com.yarl.pruebatecnica.pruebatecnica.domain.model.Client;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountService implements AccountServicePort {

    private final ClientPersistencePort clientPersistencePort;
    private final AccountPersistencePort accountPersistencePort;

    public AccountService(ClientPersistencePort clientPersistencePort, AccountPersistencePort accountPersistencePort) {
        this.clientPersistencePort = clientPersistencePort;
        this.accountPersistencePort = accountPersistencePort;
    }

    @Override
    public Account crearCuenta(Long idCliente, Account account) {
        // 1. Validar cliente
        Client client = clientPersistencePort.getClientById(idCliente)
                .orElseThrow(ClientNotFoundException::new);

        // 2. Validar tipo de cuenta
        if (account.getTipoProducto() == null) {
            throw new IllegalArgumentException("Debe especificar el tipo de cuenta (AHORROS o CORRIENTE)");
        }

        // 3. Generar número de cuenta automáticamente
        String numeroCuenta = generarNumeroCuenta(account.getTipoProducto());
        account.setNumeroCuenta(numeroCuenta);

        // 4. Estado y validaciones
        if (account.getTipoProducto() == TipoProducto.AHORROS) {
            account.setEstadoCuenta(EstadoCuenta.ACTIVO);
            if (account.getSaldo() != null && account.getSaldo().compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("La cuenta de ahorros no puede tener saldo negativo");
            }
        }

        // 5. Asignar cliente y fechas
        account.setClient(client);
        account.setFechaCreacion(LocalDate.now());
        account.setFechaModificacion(LocalDate.now());

        // 6. Guardar cuenta y devolver la instancia creada
        return accountPersistencePort.save(client.getId(), account);
    }


    private String generarNumeroCuenta(TipoProducto tipo) {
        String prefijo = tipo == TipoProducto.AHORROS ? "53" : "33";
        String randomPart = String.format("%08d", (int) (Math.random() * 100_000_000));
        return prefijo + randomPart; // total 10 dígitos
    }

    @Override
    public Account getAccountById(Long idAccount) {
        return accountPersistencePort.getAccountById(idAccount).orElse(null);
    }

    @Override
    public List<Account> getAccountsByClientId(Long idCliente) {
        return accountPersistencePort.getAccountsByClientId(idCliente);
    }

    @Override
    public Account updateAccount(Long idAccount, Account account) {
        // Ejemplo: permitir actualizar estado (ACTIVA/INACTIVA)
        Account existing = accountPersistencePort.getAccountById(idAccount)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));

        existing.setEstadoCuenta(account.getEstadoCuenta());
        existing.setFechaModificacion(LocalDate.now());

        accountPersistencePort.save(existing.getClient().getId(), existing);
        return existing;
    }

    @Override
    public void eliminarAccount(Long idAccount) {
        accountPersistencePort.getAccountById(idAccount)
                .orElseThrow(AccountNotFoundException::new);
        accountPersistencePort.deleteById(idAccount); // ahora sí elimina
    }

    @Override
    public Client RelatedClientToAccount(Long idAccount) {
        return accountPersistencePort.RelatedClientToAccount(idAccount);
    }
}
