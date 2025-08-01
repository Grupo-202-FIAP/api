package com.postech.fastfood.adapter.driven.persistence.repository.payment;

import com.postech.fastfood.adapter.driven.persistence.entity.PaymentEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaymentEntityRepository extends JpaRepository<PaymentEntity, UUID> {
}
