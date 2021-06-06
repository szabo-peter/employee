package com.example.employee.controller;

import com.example.employee.model.Company;
import com.example.employee.model.dto.CompanyResponse;
import com.example.employee.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/company")
@AllArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("")
    public List<CompanyResponse> getAllCompany(){
        return companyService.getAllCompany();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCompany(@RequestBody Company company){
        companyService.createCompany(company);
    }

    @GetMapping("/{id}")
    public Optional<CompanyResponse> getACompanyById(@PathVariable String id){
        return companyService.getACompanyById(id);
    }
    @GetMapping("/name/{name}")
    public Optional<CompanyResponse> getACompanyByName(@PathVariable String name){
        return companyService.getACompanyByName(name);
    }
}
