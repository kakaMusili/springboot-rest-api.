package com.codesol.springbootbackend.service;

import com.codesol.springbootbackend.model.Employee;

import java.util.List;

public interface EmployeeServiceI {
    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployee();

    Employee getEmployeeById(long id);

    Employee getSingleEmployee(long id);

    Employee updateEmployee(Employee employee, long id);

    void deleteEmployeeById(long id);
}
