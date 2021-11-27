package com.codesol.springbootbackend.controller;

import com.codesol.springbootbackend.model.Employee;
import com.codesol.springbootbackend.service.EmployeeServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeServiceI employeeServiceI;

    public EmployeeController(EmployeeServiceI employeeServiceI) {
        this.employeeServiceI = employeeServiceI;
    }

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeServiceI.saveEmployee(employee), HttpStatus.CREATED);
    }

    @GetMapping
   public List<Employee> getEmployees(){
        return  employeeServiceI.getAllEmployee();
   }

   @GetMapping("{id}")
   public ResponseEntity<Employee> getEmployeeById(
           @PathVariable( "id") long id){

       return new ResponseEntity<Employee>(employeeServiceI.getEmployeeById(id), HttpStatus.OK);

   }

   @PutMapping("{id}")
   public ResponseEntity<Employee> updateEmployeeById(
           @PathVariable("id" ) long id,@RequestBody Employee employee
   ){
      return  new ResponseEntity<Employee>(employeeServiceI.updateEmployee(employee,id), HttpStatus.OK);

   }

   @DeleteMapping("{id}")
   public ResponseEntity<String> deteleEmployeeById(
           @PathVariable("id") long id
   ){

        employeeServiceI.deleteEmployeeById(id);
        return  new ResponseEntity<String>("Employee Deleted successfully", HttpStatus.OK);
   }
}
