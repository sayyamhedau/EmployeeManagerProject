package com.app;

import com.app.model.Employee;
import com.app.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class EmployeeManagerProjectApplication {
	@Autowired
	private EmployeeRepository employeeRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeManagerProjectApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagerProjectApplication.class, args);
	}

	public CommandLineRunner saveRecords(){
		return args -> {
			List<Employee> employeeList = Arrays.asList(new Employee(2L, "Sayyam", "Hedau", "8007390103", "sayyamhedau01@gmail.com", "Software Developer", null, "002138"),
					new Employee(3L, "Anisha", "Deshmukh", "8007390101", "anisha@gmail.com", "Software Developer", null, "002132"));
			employeeRepository.saveAllAndFlush(employeeList);
			LOGGER.info("Employees are Successfully Saved to DB");
		};
	}
}
