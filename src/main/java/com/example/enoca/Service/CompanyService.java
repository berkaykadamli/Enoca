package com.example.enoca.Service;

import com.example.enoca.Exception.CompanyNotFoundException;
import com.example.enoca.Model.Company;
import com.example.enoca.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Company save(Company company) {
        return companyRepository.save(company);
    }

    public Company findById(int id) {
        return companyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException(id));
    }

    public void deleteById(int id) {
        companyRepository.deleteById(id);
    }

    public Company update(Company company, int id) {
        return companyRepository.findById(id).map(cmp -> {
            cmp.setEmployees(company.getEmployees());
            cmp.setName(company.getName());
            cmp.setLocation(company.getLocation());
            cmp.setWebsite(company.getWebsite());
            return companyRepository.save(cmp);
        }).orElseGet(() -> {
            company.setId(id);
            return companyRepository.save(company);
        });
    }

}
