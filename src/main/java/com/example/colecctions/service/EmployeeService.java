package com.example.colecctions.service;

import com.example.colecctions.dto.Employee;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName);

    Employee removeEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    List<Employee> findAll();
}
