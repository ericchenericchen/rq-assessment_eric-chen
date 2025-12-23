package com.challenge.api; // let test file access SpringBoot MVC

import static org.junit.jupiter.api.Assertions.*;

import com.challenge.api.controller.EmployeeController;
import com.challenge.api.model.Employee;
import com.challenge.api.model.IEmployee;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;

// @SpringBootTest
class TestingWebApplicationTests {

    // @Autowired
    // private EntryLevelJavaChallengeApplication _app;

    // I couldn't get SpringBootTest to work properly, so I decided to test the controller directlly
    private EmployeeController _controller;

    @BeforeEach
    void setup() {
        _controller = new EmployeeController();
    }

    @Test
    void basicInsertAndReadTest() {
        IEmployee testEmployee = new IEmployee();

        testEmployee.setFirstName("eric");
        testEmployee.setLastName("chen");
        testEmployee.setSalary(90000);
        testEmployee.setAge(22);
        testEmployee.setJobTitle("tester");
        testEmployee.setEmail("ericsc@usc.edu");

        Employee created = _controller.createEmployee(testEmployee);

        assertNotNull(created.getUuid(), "UUID should be generated");
        assertEquals("eric", created.getFirstName());
        assertEquals("chen", created.getLastName());
        assertEquals("eric chen", created.getFullName());
        assertEquals(90000, created.getSalary());
        assertEquals(22, created.getAge());
        assertEquals("tester", created.getJobTitle());
        assertEquals("ericsc@usc.edu", created.getEmail());

        System.out.println("successfully created employee!");

        UUID uuid = created.getUuid();

        List<Employee> allEmployees = _controller.getAllEmployees();
        assertEquals(allEmployees.size(), 1);
        assertEquals(allEmployees.get(0).getUuid(), uuid);

        System.out.println("successfully checked all employees!");

        Employee singleEmployee = _controller.getEmployeeByUuid(uuid);

        assertNotNull(singleEmployee.getUuid(), "UUID should be generated");
        assertEquals("eric", singleEmployee.getFirstName());
        assertEquals("chen", singleEmployee.getLastName());
        assertEquals("eric chen", singleEmployee.getFullName());
        assertEquals(90000, singleEmployee.getSalary());
        assertEquals(22, singleEmployee.getAge());
        assertEquals("tester", singleEmployee.getJobTitle());
        assertEquals("ericsc@usc.edu", singleEmployee.getEmail());

        System.out.println("successfully checked single employee!");
    }
}
