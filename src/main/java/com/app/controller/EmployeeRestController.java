package com.app.controller;

import com.app.exception.EmployeeNotFoundException;
import com.app.model.Employee;
import com.app.service.EmployeeService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1.0")
public class EmployeeRestController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/welcome/{employee_name}")
    public ResponseEntity<String> getWelcomeMessage(@PathVariable String employee_name) {
        return ResponseEntity.ok("Hello ".concat(employee_name).concat(", Welcome to My Manager Application"));
    }

    @GetMapping(value = "/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployeeList());
    }

    @GetMapping(value = "/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(final @NonNull @PathVariable Long id) {
        Optional<Employee> optionalEmployee = employeeService.getEmployeeById(id);
        if (!optionalEmployee.isPresent()) throw new EmployeeNotFoundException("Employee ID " + id + " Not Found");
        return ResponseEntity.ok(optionalEmployee.get());
    }
}
