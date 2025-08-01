package com.postech.fastfood.infrastructure.gateways.employee;

import com.postech.fastfood.domain.Employee;

public interface CreateEmployeeUseCase {

    Employee execute(Employee employee);

}
