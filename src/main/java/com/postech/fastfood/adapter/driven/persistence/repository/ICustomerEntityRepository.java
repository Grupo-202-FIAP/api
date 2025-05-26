package com.postech.fastfood.adapter.driven.persistence.repository;

import com.postech.fastfood.adapter.driven.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ICustomerEntityRepository extends JpaRepository<CustomerEntity, UUID> {
    Optional<CustomerEntity> findByCpf(String cpf);

    Optional<CustomerEntity> findByEmail(String email);
}
