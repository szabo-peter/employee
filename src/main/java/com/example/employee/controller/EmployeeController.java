package com.example.employee.controller;

import com.example.employee.exception.CsokiAndrasException;
import com.example.employee.model.Employee;
import com.example.employee.model.dto.EmployeeResponse;
import com.example.employee.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/employee")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("")
    public List<EmployeeResponse> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEmployee(@RequestBody Employee employee){
        employeeService.createEmployee(employee);
    }

    @GetMapping("/{id}")
    public Optional<EmployeeResponse> getAnEmployeeById(@PathVariable String id){
        return employeeService.getAnEmployeeById(id);
    }
}
