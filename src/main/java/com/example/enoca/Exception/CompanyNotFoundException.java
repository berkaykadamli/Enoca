package com.example.enoca.Exception;

public class CompanyNotFoundException extends RuntimeException  {

    public CompanyNotFoundException(int id) {
        super(id+" Company Not Found");
    }
}
