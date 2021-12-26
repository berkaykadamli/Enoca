package com.example.enoca.Controller;


import com.example.enoca.Model.Company;
import com.example.enoca.Model.Employee;
import com.example.enoca.Service.CompanyService;
import com.example.enoca.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
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
        Company company = companyService.findById(companyId);
        company.getEmployees().add(employee);
        companyService.save(company);
        employeeService.save(employee);
        return new ResponseEntity<>(employee.getName() + " inserted to this company : " + company.getName(), HttpStatus.CREATED);
    }


    //Employee List all
    @GetMapping("/employee/all")
    public ResponseEntity<List<Employee>> findAll() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }


    //Employe save without Company
    @PostMapping("/employee/save")
    public Employee saveOne(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }


    @PutMapping("/employee/update/{id}")
    public Employee update(@RequestBody Employee newEmployee, @PathVariable int id) {
        return employeeService.update(newEmployee, id);
    }


    @DeleteMapping("/employee/delete/{id}")
    public void deleteById(@PathVariable int id) {
        employeeService.deleteById(id);
    }


}
