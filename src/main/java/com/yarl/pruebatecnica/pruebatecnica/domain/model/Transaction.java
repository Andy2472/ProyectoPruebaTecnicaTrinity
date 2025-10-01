package com.yarl.pruebatecnica.pruebatecnica.domain.model;

import com.yarl.pruebatecnica.pruebatecnica.domain.enums.TipoTransacciones;

public class Transaction {
    private Long id;
    private TipoTransacciones tipoTransacciones;

    public Long getId() {
        return id;
    }

    public TipoTransacciones getTipoTransacciones() {
        return tipoTransacciones;
    }
}
