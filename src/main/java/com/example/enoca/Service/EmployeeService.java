package com.example.enoca.Service;

import com.example.enoca.Model.Employee;
import com.example.enoca.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee findById(int id) {
        return employeeRepository.findById(id);
    }

    public Employee update(Employee employee, int id) {
        Employee oldEmployee = employeeRepository.findById(id);
        oldEmployee.setName(employee.getName());
        oldEmployee.setSurname(employee.getSurname());
        oldEmployee.setEmail(employee.getEmail());
        oldEmployee.setCompany(employee.getCompany());
        oldEmployee.setSalary(employee.getSalary());

        return employeeRepository.save(oldEmployee);
    }

    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}
