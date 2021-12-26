package com.example.enoca.Controller;

import com.example.enoca.Model.Company;
import com.example.enoca.Model.Employee;
import com.example.enoca.Service.CompanyService;
import com.example.enoca.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listCompany", companyService.findAll());
        return "index-company";
    }


    @RequestMapping(value = "/company/add", method = RequestMethod.GET)
    public String showAddCompanyPage(Model model) {
        model.addAttribute("company", new Company());
        return "company_add";
    }

    @RequestMapping(value = "/company/save", method = RequestMethod.POST)
    public String saveCompany(@ModelAttribute Company company) {
        companyService.save(company);
        return "redirect:/";
    }

    @RequestMapping(value = "/company/edit/{companyId}", method = RequestMethod.GET)
    public String showEditCompanyPage(Model model, @PathVariable int companyId) {
        model.addAttribute("company", new Company());
        model.addAttribute("oldCompany", companyService.findById(companyId));
        return "company_edit";
    }

    @RequestMapping(value = "/company/edit/save/{companyId}", method = RequestMethod.POST)
    public String saveEditCompany(@ModelAttribute Company newCompany, @PathVariable int companyId) {
        Company oldCompany = companyService.findById(companyId);
        oldCompany.setName(newCompany.getName());
        oldCompany.setWebsite(newCompany.getWebsite());
        oldCompany.setLocation(newCompany.getLocation());
        companyService.save(oldCompany);
        return "redirect:/";
    }

    @RequestMapping(value = "/company/delete/{id}", method = RequestMethod.GET)
    public String deleteCompany(@PathVariable int id) {
        companyService.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/company/{companyId}/employee", method = RequestMethod.GET)
    public String showEmployees(Model model, @PathVariable int companyId) {
        Company company = companyService.findById(companyId);
        model.addAttribute("listEmployees", company.getEmployees());
        model.addAttribute("name", company.getName());
        model.addAttribute("id", company.getId());
        return "index-employee";
    }

    @RequestMapping(value = "/employee/edit/{id}/company/{companyId}", method = RequestMethod.GET)
    public String showEditEmployeePage(Model model, @PathVariable int id, @PathVariable int companyId) {
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", new Employee());
        model.addAttribute("oldEmployee", employee);
        model.addAttribute("companyId", companyId);
        return "employee_edit";
    }

    @RequestMapping(value = "/employee/edit/save/{id}/company/{companyId}", method = RequestMethod.POST)
    public String saveEditEmployee(@ModelAttribute Employee newEmployee, @PathVariable int id, @PathVariable int companyId) {
        Employee employee = employeeService.findById(id);
        employee.setName(newEmployee.getName());
        employee.setSurname(newEmployee.getSurname());
        employee.setEmail(newEmployee.getEmail());
        employee.setSalary(newEmployee.getSalary());
        employeeService.save(employee);
        return "redirect:/company/{companyId}/employee";
    }


    @RequestMapping(value = "/employee/add/{id}", method = RequestMethod.GET)
    public String showAddEmployeePage(Model model, @PathVariable int id) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("companyId", id);
        return "employee_add";
    }


    @RequestMapping(value = "/employee/save/{companyId}", method = RequestMethod.POST)
    public String saveEmployee(@ModelAttribute Employee employee, @PathVariable int companyId) {
        Company company = companyService.findById(companyId);
        company.getEmployees().add(employee);
        companyService.save(company);
        employeeService.save(employee);
        return "redirect:/company/{companyId}/employee";
    }

    @RequestMapping(value = "/{companyId}/employee/delete/{id}", method = RequestMethod.GET)
    public String deleteEmployee(@PathVariable int id,@PathVariable int companyId) {
        employeeService.deleteById(id);
        return "redirect:/company/{companyId}/employee";
    }
}
