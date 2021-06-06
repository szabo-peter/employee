package com.example.employee.service;

import com.example.employee.model.Company;
import com.example.employee.model.dto.CompanyResponse;
import com.example.employee.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public List<CompanyResponse> getAllCompany() {

        List<Company> companys= companyRepository.findAll();
        return companys.stream().map(CompanyResponse::createCompanyResponse).collect(Collectors.toList());
    }

    public void createCompany(Company company) {
        companyRepository.save(
                company
                        .toBuilder()
                        .id(UUID.randomUUID().toString())
                        .build());
    }

    public Optional<CompanyResponse> getACompanyById(String id) {
        Optional<Company> company = companyRepository.findById(id);
        return company.stream().map(CompanyResponse::createCompanyResponse).findAny();
    }

    public Optional<CompanyResponse> getACompanyByName(String name) {
        Optional<Company> company = companyRepository.findByNameContaining(name);
        return company.stream().map(CompanyResponse::createCompanyResponse).findAny();
    }
}
