package com.yarl.pruebatecnica.pruebatecnica.utils;

import lombok.Getter;

@Getter
public enum ErrorCatalog {

    /* Aquí vamos a poner los codigos, por ejemplo, para el "Cliente no encontrado", le vamos a poner por ejemplo
    "Erro - Cliente noo encontrado #1" donde el "#1" es como un codigo que nosotros le pondríamos, entiendes? */

    CLIENT_NOT_FOUND("ERR_CLIENT_001", "CLIENT NOT FOUND"),

    INVALID_CLIENT("ERR_CLIENT_002", "INVALID CLIENT PARAMETER"),

    GENERIC_ERROR("GENERIC_001", "INTERNAL SERVER ERROR");

    private final String code;
    private final String message;

    ErrorCatalog(String code, String message){
        this.code = code;
        this.message = message;
    }

}
