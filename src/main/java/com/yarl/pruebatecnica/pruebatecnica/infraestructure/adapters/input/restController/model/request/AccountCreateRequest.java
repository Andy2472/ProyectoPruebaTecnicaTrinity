package com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.input.restController.model.request;

import com.yarl.pruebatecnica.pruebatecnica.domain.enums.EstadoCuenta;
import com.yarl.pruebatecnica.pruebatecnica.domain.enums.TipoProducto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreateRequest {

    @NotNull(message = "El campo tipo producto no puede ser nulo")
    private TipoProducto tipoProducto;

    @NotNull(message = "El campo numero Cuenta no puede ser nulo")
    private String numeroCuenta;

    @NotNull(message = "El campo Estado Cuenta no puede ser nulo")
    private EstadoCuenta estadoCuenta;

    @NotNull(message = "El campo saldo no puede ser nulo")
    private BigDecimal saldo;

    @NotNull(message = "El campo exentaGMF no puede ser nulo")
    private Boolean exentaGMF;

    @PastOrPresent(message = "La fecha de creación no puede ser futura")
    private LocalDate fechaCreacion;

    @PastOrPresent(message = "La fecha de modificación no puede ser futura")
    private LocalDate fechaModificacion;
}
