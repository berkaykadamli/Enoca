package com.example.enoca.Controller;


import com.example.enoca.Model.Company;
import com.example.enoca.Service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class CompanyController {


    @Autowired
    private CompanyService companyService;

    @GetMapping("/company/all")
    public ResponseEntity<List<Company>> findAll() {
        return new ResponseEntity<>(companyService.findAll(), HttpStatus.OK);
    }


    @PostMapping("/company/save")
    public ResponseEntity<Company> save(@RequestBody Company company) {
        return new ResponseEntity<>(companyService.save(company),HttpStatus.OK);
    }

    @PutMapping("/company/{id}")
    public Company update(@RequestBody Company company, @PathVariable int id) {
        return companyService.update(company, id);
    }

    @DeleteMapping("/company/delete/{id}")
    public void delete(@PathVariable int id) {
        companyService.deleteById(id);
    }

}
