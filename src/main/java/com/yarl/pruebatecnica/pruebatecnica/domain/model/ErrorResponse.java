package com.yarl.pruebatecnica.pruebatecnica.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
public class ErrorResponse {

    /* Todo esto se usa en el archivo GlobarControllerAdvice */

    private String CodigoError;
    private String MessageError;
    private List<String> detallesDeError;
    private LocalDateTime marcaDeTiempo;
}
