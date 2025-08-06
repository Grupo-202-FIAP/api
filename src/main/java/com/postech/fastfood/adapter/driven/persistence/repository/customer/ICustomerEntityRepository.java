package com.postech.fastfood.adapter.driven.persistence.repository.customer;

import com.postech.fastfood.adapter.driven.persistence.entity.CustomerEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerEntityRepository extends JpaRepository<CustomerEntity, UUID> {
    Optional<CustomerEntity> findByCpf(String cpf);

    Optional<CustomerEntity> findByEmail(String email);

    Optional<CustomerEntity> findById(UUID uuid);
}
