package com.example.employee.repository;

import com.example.employee.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company,String> {

   Optional<Company> findByNameContaining(String name);
}
