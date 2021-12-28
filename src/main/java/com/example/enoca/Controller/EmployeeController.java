package com.example.enoca.Controller;


import com.example.enoca.Exception.CompanyNotFoundException;
import com.example.enoca.Exception.EmployeeNotFoundException;
import com.example.enoca.Model.Company;
import com.example.enoca.Model.Employee;
import com.example.enoca.Service.CompanyService;
import com.example.enoca.Service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CompanyService companyService;


    //Specific Company Employee List All
    @GetMapping("/employee/all/company/{companyId}")
    public ResponseEntity<List<Employee>> getAllEmployees(@PathVariable int companyId) {
        return new ResponseEntity<>(companyService.findById(companyId).getEmployees(), HttpStatus.OK);
    }


    //Save into a specific company an or more employee
    @PostMapping("/employee/save/company/{companyId}")
    public ResponseEntity<String> save(@RequestBody Employee employee, @PathVariable int companyId) {
        try {
            Company company = companyService.findById(companyId);
            company.getEmployees().add(employee);
            companyService.save(company);
            employeeService.save(employee);
            return new ResponseEntity<>(employee.getName() + " inserted to this company : " + company.getName(), HttpStatus.CREATED);
        }
        catch (Exception e){
            log.info(e.getMessage());
            return new ResponseEntity<String>(new CompanyNotFoundException(companyId).getMessage(),HttpStatus.BAD_REQUEST);
        }

    }


    //Employee List all
    @GetMapping("/employee/all")
    public ResponseEntity<List<Employee>> findAll() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }


    //Employe save without Company
    @PostMapping("/employee/save")
    public ResponseEntity<Employee> saveOne(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.save(employee), HttpStatus.OK);
    }


    @PutMapping("/employee/update/{id}")
    public ResponseEntity<String> update(@RequestBody Employee newEmployee, @PathVariable int id) {
        try {
            Employee oldEmployee = employeeService.findById(id);
            oldEmployee.setName(newEmployee.getName());
            oldEmployee.setSurname(newEmployee.getSurname());
            oldEmployee.setEmail(newEmployee.getEmail());
            oldEmployee.setCompany(newEmployee.getCompany());
            oldEmployee.setSalary(newEmployee.getSalary());
            employeeService.update(oldEmployee);
            return new ResponseEntity<String>(oldEmployee.getName() + " has updated !", HttpStatus.OK);
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResponseEntity<String>(new EmployeeNotFoundException(id).getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/employee/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        try {
            Employee employee = employeeService.findById(id);
            employeeService.deleteById(id);
            return new ResponseEntity<String>(employee.getName() + " has deleted !", HttpStatus.OK);
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResponseEntity<String>(new EmployeeNotFoundException(id).getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


}
