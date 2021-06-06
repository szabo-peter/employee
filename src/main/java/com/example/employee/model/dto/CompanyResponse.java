package com.example.employee.model.dto;

import com.example.employee.model.Assignment;
import com.example.employee.model.Company;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyResponse {

    private String name;
    private int leaderCount;
    private int workerCount;

    public static CompanyResponse createCompanyResponse(Company company){
        return new CompanyResponse(company.getName(),
                (int)company.getEmployeeList().stream().filter(employee -> employee.getAssignment().equals(Assignment.LEADER)).count(),
                (int)company.getEmployeeList().stream().filter(employee -> employee.getAssignment().equals(Assignment.WORKER)).count()
                );
    }
}
