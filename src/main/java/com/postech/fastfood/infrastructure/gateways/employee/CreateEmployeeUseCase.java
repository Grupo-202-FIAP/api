package com.postech.fastfood.core.usecase.employee;

import com.postech.fastfood.core.domain.Employee;

public interface CreateEmployeeUseCase {

    Employee execute(Employee employee);

}
