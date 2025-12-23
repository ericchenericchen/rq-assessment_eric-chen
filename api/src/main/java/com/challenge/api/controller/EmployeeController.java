package com.challenge.api.controller;

import com.challenge.api.model.Employee;
import com.challenge.api.service.EmployeeService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * Fill in the missing aspects of this Spring Web REST Controller. Don't forget to add a Service layer.
 */
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    @Autowired
    private final EmployeeService _employeeService;

    public EmployeeController() {
        _employeeService = new EmployeeService();
    }

    /**
     * @implNote Need not be concerned with an actual persistence layer. Generate mock Employee models as necessary.
     * @return One or more Employees.
     */
    @GetMapping
    public List<Employee> getAllEmployees() {
        List<Employee> allEmployees;

        try {
            allEmployees = _employeeService.getAllEmployees();
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error in EmployeeService's getAllEmployees method");
        }

        return allEmployees; // always return allEmployees, even if empty list.
    }

    /**
     * @implNote Need not be concerned with an actual persistence layer. Generate mock Employee model as necessary.
     * @param uuid Employee UUID
     * @return Requested Employee if exists
     */
    @GetMapping("/{uuid}")
    public Employee getEmployeeByUuid(UUID uuid) {
        // First, parse that UUID is valid
        if (uuid == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid UUID");

        Employee target = _employeeService.getEmployee(uuid);
        if (target == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No employee with such UUID");
        }
        try {
            return _employeeService.getEmployee(uuid);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error in EmployeeService's getEmployee method");
        }
    }

    /**
     * @implNote Need not be concerned with an actual persistence layer.
     * @param requestBody hint!
     * @return Newly created Employee
     */
    @PostMapping
    public Employee createEmployee(Object requestBody) {
        // request body can realistically come in many forms: since requestBody is
        // passed as an object, I'm assuming the payload was already parsed from raw 
        // HTML/JSON format into an object that implements Employee.
        //
        // If not parsed into a instanceof Employee, it's straightforward to assign
        // the data to an IEmployee object.

        Employee newEmployee;

        try {
            newEmployee = (Employee) requestBody;
        } catch (ClassCastException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "requestBody not compatible with Employee interface.");
        }

        if (newEmployee.getFirstName() == null
                || newEmployee.getLastName() == null
                || newEmployee.getSalary() == 0
                || newEmployee.getAge() == 0
                || newEmployee.getJobTitle() == null
                || newEmployee.getEmail() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "requestBody missing required fields.");
        }

        try {
            return _employeeService.createEmployee(newEmployee);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error in EmployeeService's createEmployee method");
        }
    }
}
