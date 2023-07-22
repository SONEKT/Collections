package com.example.colecctions.service;

import com.example.colecctions.dto.Employee;
import com.example.colecctions.exeptions.EmployeeAlreadyAddedException;
import com.example.colecctions.exeptions.EmployeeNotFoundException;
import com.example.colecctions.exeptions.EmployeeStorageFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private List<Employee> employees;

    private static final int EMPLOYEE_MAX_SIZE = 10;


    public EmployeeServiceImpl() {
        this.employees = new ArrayList<>();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        if (employees.size() == EMPLOYEE_MAX_SIZE) {
            throw new EmployeeStorageFullException("Превышен лимит сотрудников фирмы");
        }
        Employee employee = new Employee(firstName, lastName);

        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("В коллекции уже есть такой сотрудник");
        }

        employees.add(employee);

        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);

        if (!employees.remove(employee)) {
            throw new EmployeeNotFoundException("Данного сотрудника нет в коллекции");
        }
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        return employees;
    }
}
