package com.example.colecctions.service;

import com.example.colecctions.dto.Employee;
import com.example.colecctions.exeptions.EmployeeNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentServiceImplTest {
    EmployeeServiceImpl underTest = new EmployeeServiceImpl();
    DepartmentServiceImpl underTestDepart = new DepartmentServiceImpl(underTest);
    Employee Ivan = new Employee("IVAN", "IVANOV", 1, 1);
    Employee Max = new Employee("Maxim", "Maximov", 1, 10);
    Employee Vlad = new Employee("Vlad", "Vladov", 2, 100);

    @Test
    void findMaxSalaryEmployee_throwException() {
        EmployeeNotFoundException ex =
                assertThrows(EmployeeNotFoundException.class, () -> underTestDepart.findMaxSalaryEmployee(1));
        assertEquals("Нет сотрудников в отделе " + 1, ex.getMessage());
    }

    @Test
    void findMaxSalaryEmployee_MaxSalary() {
        underTest.addEmployee(Ivan.getFirstName(), Ivan.getLastName(),
                Ivan.getDepartment(), Ivan.getSalary());
        underTest.addEmployee(Max.getFirstName(), Max.getLastName(),
                Max.getDepartment(), Max.getSalary());
        underTest.addEmployee(Vlad.getFirstName(), Vlad.getLastName(),
                Vlad.getDepartment(), Vlad.getSalary());

        Employee result = underTestDepart.findMaxSalaryEmployee(1);
        assertEquals(Max, result);
    }

    @Test
    void findMinSalaryEmployee_throwException() {
        EmployeeNotFoundException ex =
                assertThrows(EmployeeNotFoundException.class, () -> underTestDepart.findMinSalaryEmployee(1));
        assertEquals("Нет сотрудников в отделе " + 1, ex.getMessage());
    }

    @Test
    void findMinSalaryEmployee_MinSalary() {
        underTest.addEmployee(Ivan.getFirstName(), Ivan.getLastName(),
                Ivan.getDepartment(), Ivan.getSalary());
        underTest.addEmployee(Max.getFirstName(), Max.getLastName(),
                Max.getDepartment(), Max.getSalary());
        underTest.addEmployee(Vlad.getFirstName(), Vlad.getLastName(),
                Vlad.getDepartment(), Vlad.getSalary());

        Employee result = underTestDepart.findMinSalaryEmployee(1);
        assertEquals(Ivan, result);
    }

    @Test
    void getAll_FindByDepartment() {
        underTest.addEmployee("IVAN", "IVANOV", 1, 1);
        underTest.addEmployee("Maxim", "Maximov", 1, 10);
        underTest.addEmployee("Vlad", "Vladov", 2, 100);

        List<Employee> expected = new ArrayList<>();
        expected.add(Max);
        expected.add(Ivan);

        Collection<Employee> result = underTestDepart.getAll(1);
        assertEquals(expected, result);

    }

    @Test
    void getAllGropingByDepartment_FindAll() {
        underTest.addEmployee("IVAN", "IVANOV", 1, 1);
        underTest.addEmployee("Maxim", "Maximov", 1, 10);
        underTest.addEmployee("Vlad", "Vladov", 2, 100);

        List<Employee> employees = new ArrayList<>();
        employees.add(Max);
        employees.add(Ivan);

        List<Employee> employees2 = new ArrayList<>();
        employees2.add(Vlad);

        Map<Integer, List<Employee>> expected = new HashMap<>();
        expected.put(2, employees2);
        expected.put(1, employees);

        Map<Integer, List<Employee>> result = underTestDepart.getAllGropingByDepartment();
        assertEquals(expected, result);
    }
}