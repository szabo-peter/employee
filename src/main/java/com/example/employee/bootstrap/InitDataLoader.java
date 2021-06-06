package com.example.employee.bootstrap;

import com.example.employee.model.Assignment;
import com.example.employee.model.Company;
import com.example.employee.model.Employee;
import com.example.employee.model.dto.CompanyResponse;
import com.example.employee.model.dto.EmployeeResponse;
import com.example.employee.repository.CompanyRepository;
import com.example.employee.repository.EmployeeRepositroy;
import com.example.employee.service.CompanyService;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitDataLoader implements CommandLineRunner {

    private final EmployeeRepositroy employeeRepositroy;
    private final CompanyRepository companyRepository;

    @Bean
    public Faker faker() {
        return new Faker(Locale.forLanguageTag("hu-HU"));
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        saveCompanies();
        List<Company> companies = companyRepository.findAll();
        saveEmployees(companies);

    }

    @Transactional
    private void saveEmployees(List<Company> companies) {
        List<Employee> employeeList = employeeRepositroy.saveAll(
                newEmployees(companies)
        );

        log.info("Generated {} employee", employeeList.size());
    }

    public List<Employee> newEmployees(List<Company> companyList){
        return IntStream.range(0, 100)
                .mapToObj(value -> Employee.builder()
                        .id(UUID.randomUUID().toString())
                        .name(faker().name().fullName())
                        .salary(faker().number().numberBetween(200000,300000))
                        .assignment(faker().number().numberBetween(1,3) == 1?
                                Assignment.LEADER:Assignment.WORKER
                                )
                        .company(companyList.get(new Random().nextInt(companyList.size())))
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional
    private void saveCompanies() {
        List<Company> companies = companyRepository.saveAll(
                newCompanies()
        );

        log.info("Generated {} companies", companies.size());
    }

    public List<Company> newCompanies(){
        return IntStream.range(0,5).mapToObj(
                value-> Company.builder()
                        .id(UUID.randomUUID().toString())
                        .name(faker().company().name())
                        .build()
        ).collect(Collectors.toList());
    }

}



