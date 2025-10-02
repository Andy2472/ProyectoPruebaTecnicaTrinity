package com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.input.restController.model.response;

import com.yarl.pruebatecnica.pruebatecnica.domain.model.Account;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse {
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String nombres;
    private String apellidos;
    private String correo;
    private LocalDate fechaNacimiento;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
}
