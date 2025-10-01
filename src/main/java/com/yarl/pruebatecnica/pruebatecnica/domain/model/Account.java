package com.yarl.pruebatecnica.pruebatecnica.domain.model;

import com.yarl.pruebatecnica.pruebatecnica.domain.enums.EstadoCuenta;
import com.yarl.pruebatecnica.pruebatecnica.domain.enums.TipoProducto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Account {

    private Long id;
    private TipoProducto tipoCuenta;
    private String numeroCuenta;
    private EstadoCuenta estadoCuenta;
    private BigDecimal saldo;
    private Boolean exentaGMF;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;

    private Client cliente;

    public Long getId() {
        return id;
    }

    public TipoProducto getTipoCuenta() {
        return tipoCuenta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public EstadoCuenta getEstadoCuenta() {
        return estadoCuenta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public Boolean getExentaGMF() {
        return exentaGMF;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDate getFechaModificacion() {
        return fechaModificacion;
    }

    public Client getCliente() {
        return cliente;
    }
}
