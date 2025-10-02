package com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.output.persistence.repository;

import com.yarl.pruebatecnica.pruebatecnica.infraestructure.adapters.output.persistence.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaAccountRepository extends JpaRepository<AccountEntity, Long> {
    List<AccountEntity> findByClientEntity_Id(Long clientId);
    Optional<AccountEntity> findById(Long idAccount);

}
