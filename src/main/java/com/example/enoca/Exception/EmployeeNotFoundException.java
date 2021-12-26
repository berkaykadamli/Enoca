package com.example.enoca.Exception;

public class EmployeeNotFoundException extends RuntimeException{
    EmployeeNotFoundException(int id){
        super("Employee not found with " + id+ " this id");
    }
}
