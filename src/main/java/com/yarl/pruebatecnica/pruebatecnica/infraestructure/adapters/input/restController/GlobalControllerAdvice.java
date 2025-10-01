package com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.input.restController;

import com.yarl.pruebatecnica.pruebatecnica.domain.Exceptions.ClientNotFoundException;
import com.yarl.pruebatecnica.pruebatecnica.domain.model.ErrorResponse;
import com.yarl.pruebatecnica.pruebatecnica.utils.ErrorCatalog;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import static com.yarl.pruebatecnica.pruebatecnica.utils.ErrorCatalog.*;

@RestControllerAdvice
public class GlobalControllerAdvice {

    /*
        Buenas prácticas en el manejo de excepciones con @RestControllerAdvice:

        Coloca primero los métodos que manejan excepciones específicas (ej: NullPointerException, IllegalArgumentException).
        Deja al final el método que maneja excepciones genéricas (Exception).
        Razón: Spring busca el primer método que coincida con la excepción lanzada.
        Si el handler genérico está arriba, atrapará cualquier error, incluso los más específicos.
        Eso evitaría que los métodos diseñados para excepciones particulares lleguen a ejecutarse.

        En resumen: primero handlers específicos, último el handler genérico.
    */

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ClientNotFoundException.class)
    public ErrorResponse handlerClientNotFoundException(){
        return ErrorResponse.builder()
                .CodigoError(CLIENT_NOT_FOUND.getCode())
                .MessageError(CLIENT_NOT_FOUND.getMessage())
                .marcaDeTiempo(LocalDateTime.now())
                .build();
    }

    /* Cuando en la validacion, al usar @NotBlank, @NotNull, etc,
    la excepcion que arroja es "MethodArgumentNotValidException", por eso
     se pone eso en la declaracion de @ExceptionHandler */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        BindingResult result = exception.getBindingResult();

        return ErrorResponse.builder()
                .CodigoError(INVALID_CLIENT.getCode())
                .MessageError(INVALID_CLIENT.getMessage())
                .detallesDeError(result.getFieldErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList()))
                .marcaDeTiempo(LocalDateTime.now())
                .build();
    }

    /* Todas las excepciones que no encajan con las de arriba, llegarán
     a aquí*/
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handlerGenericError(Exception exception){
        return ErrorResponse.builder()
                .CodigoError(GENERIC_ERROR.getCode())
                .MessageError(GENERIC_ERROR.getMessage())
                .detallesDeError(Collections.singletonList(exception.getMessage()))
                .marcaDeTiempo(LocalDateTime.now())
                .build();

    }
}
