package com.postech.fastfood.adapter.driver.controller.dto.request;

import com.postech.fastfood.core.domain.enums.UserRole;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record CustomerCpfRequest(
        @CPF
        String cpf,
        @Enumerated @NotNull
        UserRole userRole
) {
}
