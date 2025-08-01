package com.postech.fastfood.adapter.driver.controller.dto.request;

import org.hibernate.validator.constraints.br.CPF;

public record AuthCustomerRequest(@CPF String cpf) {
}
