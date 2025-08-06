package com.postech.fastfood.application.usecases.interfaces.employee;

import com.postech.fastfood.domain.Employee;

public interface CreateEmployeeUseCase {

    Employee execute(Employee employee);

}
