package com.example.colecctions.service;

import com.example.colecctions.dto.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentService {

    Employee findMaxSalaryEmployee(int department);

    Employee findMinSalaryEmployee(int department);

    Collection<Employee> getAll(int department);

    Map<Integer, List<Employee>> getAllGropingByDepartment();

}
