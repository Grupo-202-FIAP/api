package com.postech.fastfood.infrastructure.controller.dto.request;

import org.hibernate.validator.constraints.br.CPF;

public record CustomerCpfRequest(
        @CPF
        String cpf
) {
}
