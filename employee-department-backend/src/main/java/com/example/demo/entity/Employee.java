package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Employee {
    @Id
    private String id;
    private String name;
    private String email;
    private String position;
    private double salary;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    // Getters and Setters
}
