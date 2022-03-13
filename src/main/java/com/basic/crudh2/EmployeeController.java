package com.basic.crudh2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    private EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/")
    private ResponseEntity<String> hello(){
        return new ResponseEntity<>("HELLO WORLD", HttpStatus.OK);
    }

    @PostMapping("/employee")
    private ResponseEntity<Employee> create(@RequestBody Employee employee){
        employeeRepository.save(employee);
        return new ResponseEntity<>(employee,HttpStatus.CREATED);
    }

    @GetMapping("/employee")
    private ResponseEntity<?> getAllEmployee(Employee employee){
        return new ResponseEntity<>(employeeRepository.findAll(),HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    private ResponseEntity<?> getEmployeeById(@PathVariable Long id){
        Employee employee = employeeRepository.findById(id).orElse(null);
        if(employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }

    @PutMapping("/employee/{id}")
    private ResponseEntity<?> updateEmployee(@RequestBody Employee employee, @PathVariable Long id){
        employee.setId(id);
        employeeRepository.save(employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @DeleteMapping("/employee/{id}")
    private ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        employeeRepository.deleteById(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }
}
