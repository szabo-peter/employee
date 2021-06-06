package com.example.employee.service;

import com.example.employee.exception.CsokiAndrasException;
import com.example.employee.model.Assignment;
import com.example.employee.model.Company;
import com.example.employee.model.Employee;
import com.example.employee.model.dto.EmployeeResponse;
import com.example.employee.repository.CompanyRepository;
import com.example.employee.repository.EmployeeRepositroy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepositroy employeeRepositroy;
    private final CompanyRepository companyRepository;

    public List<EmployeeResponse> getAllEmployee() {
        List<Employee> employeeList = employeeRepositroy.findAll();
        return employeeList.stream().map(EmployeeResponse::createEmployeeResponse).collect(Collectors.toList());
    }

    public void createEmployee(Employee employee){
        validateEmployee(employee);
        employeeRepositroy.save(employee.toBuilder()
                .id(UUID.randomUUID()
               .toString()).build()
        );
    }

    public Optional<EmployeeResponse> getAnEmployeeById(String id) {
        Optional<Employee> emp = employeeRepositroy.findById(id);
        return emp.stream().map(EmployeeResponse::createEmployeeResponse).findAny();
    }

    private void validateEmployee(Employee employee){
        if(!StringUtils.hasText(employee.getName())){
            throw new CsokiAndrasException("Adjál meg nevet!");
        }
        if(!employee.getAssignment().equals(Assignment.LEADER) && !employee.getAssignment().equals(Assignment.WORKER)){
            throw new CsokiAndrasException("Add meg hogy LEADER vagy WORKER!");
        }

//        Optional<Company> comp = companyRepository.findById(employee.getId());
//        if(comp.isEmpty()){
//            throw new CsokiAndrasException("Valós ID-t adj meg!");
//        }

        if(employee.getSalary() == 0){
            throw new CsokiAndrasException("Adj meg fizetést!");
        }
    }
}