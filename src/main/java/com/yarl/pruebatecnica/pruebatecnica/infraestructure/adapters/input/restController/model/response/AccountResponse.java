package com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.input.restController.model.response;

import com.yarl.pruebatecnica.pruebatecnica.domain.enums.EstadoCuenta;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {
    private String tipoProducto;
    private String numeroCuenta;
    private EstadoCuenta estadoCuenta;
    private BigDecimal saldo;
    private Boolean exentaGMF;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
}
