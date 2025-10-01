package com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.output.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
/* No es recomendable usar @Data, porque al generar los metodos "equals y hashcode",
y el equals, pues implementa todos los campos "Propiedades" para hacer la comparacion
 y eso es algo que no vamos a necesitar para las clases de Entidad, debido a que
 genera problemas de rendimiento, ¿Entendido?,
 entonces en estos casos, es mejor usar simplemente @Setter y @Getter, en otros casos si
 podríamos usar @Data, donde sea necesario hacer uso de equals y hashcode */
/*@Data*/

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String nombres;
    private String apellidos;
    private String correo;
    private LocalDate fechaNacimiento;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
}
