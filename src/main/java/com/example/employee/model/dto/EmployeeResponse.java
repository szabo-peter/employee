package com.example.employee.model.dto;

import com.example.employee.model.Assignment;
import com.example.employee.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeResponse {

    private String name;
    private String companyName;
    private Assignment assignment;
    private int salary;

    public static EmployeeResponse createEmployeeResponse(Employee employee){
        return new EmployeeResponse(employee.getName(), employee.getCompany().getName(),employee.getAssignment(),employee.getSalary());
    }
}
