package com.challenge.api.model;

import java.time.Instant;
import java.util.UUID;

public class IEmployee implements Employee {

    private UUID _employeeID;
    private String _firstName;
    private String _lastName;
    private String _fullName;
    private int _salary;
    private int _age;
    private String _jobTitle;
    private String _email;
    private Instant _contractHireDate;
    private Instant _contractTerminationDate;

    public IEmployee() {}

    @Override
    public UUID getUuid() {
        return _employeeID;
    }

    @Override
    public void setUuid(UUID uuid) {
        _employeeID = uuid;
    }

    @Override
    public String getFirstName() {
        return _firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        _firstName = firstName;
    }

    @Override
    public String getLastName() {
        return _lastName;
    }

    @Override
    public void setLastName(String lastName) {
        _lastName = lastName;
    }

    @Override
    public String getFullName() {
        return _fullName != null ? _fullName : getFirstName() + " " + getLastName();
    }

    @Override
    public void setFullName(String fullName) {
        _fullName = fullName;
    }

    @Override
    public Integer getSalary() {
        return _salary;
    }

    @Override
    public void setSalary(Integer salary) {
        _salary = salary;
    }

    @Override
    public Integer getAge() {
        return _age;
    }

    @Override
    public void setAge(Integer age) {
        _age = age;
    }

    @Override
    public String getJobTitle() {
        return _jobTitle;
    }

    @Override
    public void setJobTitle(String jobtTitle) {
        _jobTitle = jobtTitle;
    }

    @Override
    public String getEmail() {
        return _email;
    }

    @Override
    public void setEmail(String email) {
        _email = email;
    }

    @Override
    public Instant getContractHireDate() {
        return _contractHireDate;
    }

    @Override
    public void setContractHireDate(Instant date) {
        _contractHireDate = date;
    }

    @Override
    public Instant getContractTerminationDate() {
        // if employee hasn't been terminated, will keep default value for Instant, which is null
        return _contractTerminationDate;
    }

    @Override
    public void setContractTerminationDate(Instant date) {
        _contractTerminationDate = date;
    }
}
