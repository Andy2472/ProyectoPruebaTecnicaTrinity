package com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.input.restController.model.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

/* En este caso, este mismo DTO se va a usar para actualizar, porque todos los
campos de creacion, son susceptibles a cambios, en cambio, si dentro de nuestra
logica de negocio, por ejemplo, no se pudiera cambiar el campo "Correo", tendríamos
que crear otro DTO */

public class ClientCreateRequest {

    /* El @NotEmpty pasa los caracteres en blanco, por ejemplo si ponemos un espacio en blanco
    lo pasa como caracter, mientras que el @NotBlank si decta que es un campo vacío
     no lo deja pasar */

    /* Para fechas, se debe de usar @Notnull, para que no de error */


    @NotBlank(message = "El campo tipoIdentificacion no debe estar vacío")
    private String tipoIdentificacion;

    @NotEmpty(message = "El campo numeroIdentificacion no debe estar vacío")
    private String numeroIdentificacion;

    @Size(min = 2, message = "El nombre debe tener al menos 2 caracteres")
    private String nombres;

    @Size(min = 2, message = "El apellido debe tener al menos 2 caracteres")
    private String apellidos;

    @Email(message = "El campo correo debe ser válido")
    private String correo;

    @NotNull(message = "El campo fechaNacimiento no debe estar vacío")
    private LocalDate fechaNacimiento;

    private LocalDate fechaCreacion;

    private LocalDate fechaModificacion;

}
