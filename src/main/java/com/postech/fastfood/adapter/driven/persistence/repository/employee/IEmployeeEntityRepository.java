package com.postech.fastfood.adapter.driven.persistence.repository.employee;

import com.postech.fastfood.adapter.driven.persistence.entity.EmployeeEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeEntityRepository extends JpaRepository<EmployeeEntity, UUID> {
    Optional<EmployeeEntity> findByCpf(String cpf);

    Optional<EmployeeEntity> findByEmail(String email);
}
