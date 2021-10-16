package com.app.service;

import com.app.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getAllEmployeeList();
    Optional<Employee> getEmployeeById(Long id);
}
