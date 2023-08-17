package com.example.colecctions.service;

import com.example.colecctions.dto.Employee;
import com.example.colecctions.exeptions.EmployeeAlreadyAddedException;
import com.example.colecctions.exeptions.EmployeeNotFoundException;
import com.example.colecctions.exeptions.EmployeeStorageFullException;
import com.example.colecctions.exeptions.InvalidInputException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.*;
import static org.springframework.cache.interceptor.SimpleKeyGenerator.generateKey;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private Map<String, Employee> employees;

    private static final int EMPLOYEE_MAX_SIZE = 10;


    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, int department, double salary) {
        validateInput(firstName, lastName);


        if (employees.size() == EMPLOYEE_MAX_SIZE) {
            throw new EmployeeStorageFullException("Превышен лимит сотрудников фирмы");
        }

        Employee employee = new Employee(StringUtils.capitalize(firstName), StringUtils.capitalize(lastName),
                department, salary);

        String key = (firstName + lastName);


        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException("В коллекции уже есть такой сотрудник");
        }

        employees.put(key, employee);

        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        validateInput(firstName, lastName);

        Employee employee = employees.remove(firstName + lastName);
        if (Objects.isNull(employee)) {
            throw new EmployeeNotFoundException("Данного сотрудника нет в коллекции");
        } else {
            return employee;
        }


    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        validateInput(firstName, lastName);

        Employee employee = employees.get(firstName + lastName);
        if (Objects.isNull(employee)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return employee;
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees.values());
    }

    private void validateInput(String firstName, String lastName) {
        if (!(isAlpha(firstName) && isAlpha(lastName))) {
            throw new InvalidInputException();
        }
    }
}
