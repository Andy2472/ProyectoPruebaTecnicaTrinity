package com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.output.persistence.entity;

import com.yarl.pruebatecnica.pruebatecnica.domain.enums.EstadoCuenta;
import com.yarl.pruebatecnica.pruebatecnica.domain.enums.TipoProducto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TipoProducto tipoCuenta;
    private String numeroCuenta;
    @Enumerated(EnumType.STRING)
    private EstadoCuenta estadoCuenta;
    private BigDecimal saldo;
    private Boolean exentaGMF;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity clientEntity;
}
