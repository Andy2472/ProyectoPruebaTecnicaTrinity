package com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.output.persistence.repository;

import com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.output.persistence.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaClienteRepository extends JpaRepository<ClientEntity, Long> {
}
