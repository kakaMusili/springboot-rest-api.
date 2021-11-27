package com.codesol.springbootbackend.service.impl;

import com.codesol.springbootbackend.exceptions.ResourceNotFoundException;
import com.codesol.springbootbackend.model.Employee;
import com.codesol.springbootbackend.repository.EmployeeRepository;
import com.codesol.springbootbackend.service.EmployeeServiceI;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeServiceI {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
       Optional<Employee> employee=employeeRepository.findById(id);

       if(employee.isPresent()){
           return  employee.get();
       }else{
           throw  new ResourceNotFoundException("Employee", "Id", id);
       }
    }

    @Override
    public Employee getSingleEmployee(long id) {
        return employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee", "Id", id));
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {

        Employee existingEmployee=employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "Id", id)
        );

        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());

        return employeeRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployeeById(long id) {

        employeeRepository.findById(id).orElseThrow(
                () ->    new ResourceNotFoundException("Employee","Id", id)
        );

    }
}
