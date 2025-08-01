package com.postech.fastfood.infrastructure.repository.payment;

import com.postech.fastfood.infrastructure.repository.entity.PaymentEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaymentEntityRepository extends JpaRepository<PaymentEntity, UUID> {
}
