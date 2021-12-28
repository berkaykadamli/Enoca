package com.example.enoca;

import com.example.enoca.Model.Company;
import com.example.enoca.Model.Employee;
import com.example.enoca.Repository.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CompanyRepository companyRepository) {

        return args -> {
            if(companyRepository.findAll().size() == 0) {
                Company company = new Company("Enoca","enoca.com","Karabük");
                Employee berkay = new Employee("Berkay", "Kadamlı", 4250,"berkaykadamli@gmail.com");
                Employee john = new Employee("John", "Doe", 1,"johndoe@gmail.com");
                company.setEmployees(Arrays.asList(berkay, john));
                log.info("Preloading " + companyRepository.save(company));
            }

        };
    }
}