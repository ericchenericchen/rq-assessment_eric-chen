package com.challenge.api.service;

import com.challenge.api.model.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final Map<UUID, Employee> employeeRepository = new HashMap<>();

    public Employee createEmployee(Employee validatedEmployee) {
        Employee employee = new IEmployee();

        employee.setUuid(UUID.randomUUID());
        employee.setFirstName(validatedEmployee.getFirstName());
        employee.setLastName(validatedEmployee.getLastName());
        employee.setFullName(validatedEmployee.getFirstName() + " " + validatedEmployee.getLastName());
        employee.setSalary(validatedEmployee.getSalary());
        employee.setAge(validatedEmployee.getAge());
        employee.setJobTitle(validatedEmployee.getJobTitle());
        employee.setEmail(validatedEmployee.getEmail());

        employee.setContractHireDate(Instant.now());
        employee.setContractTerminationDate(null);

        employeeRepository.put(employee.getUuid(), employee);
        return employee;
    }

    public Employee getEmployee(UUID id) {
        return employeeRepository.get(id); // returns null if id not in mapping
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeRepository.values()); // returns null if no values
    }
}
