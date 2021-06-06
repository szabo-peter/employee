package com.example.employee.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder(toBuilder = true)
public class Employee {

    @Id
    private String id;
    private String name;
    private int salary;
    @Enumerated(EnumType.STRING)
    private Assignment assignment;
    @ManyToOne()
    private Company company;

}

