package com.example.colecctions.service;

import com.example.colecctions.dto.Employee;
import com.example.colecctions.exeptions.EmployeeNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {
    private final EmployeeService underTest = mock(EmployeeService.class);
    private final DepartmentServiceImpl underTestDepart = new DepartmentServiceImpl(underTest);
    private final Employee Ivan = new Employee("IVAN", "IVANOV", 1, 1);
    private final Employee Max = new Employee("Maxim", "Maximov", 1, 10);
    private final Employee Vlad = new Employee("Vlad", "Vladov", 2, 100);

    @Test
    void findMaxSalaryEmployee_throwException() {
        List<Employee> allPeople = new ArrayList<>();

        when(underTest.findAll())
                .thenReturn(allPeople);

        EmployeeNotFoundException ex =
                assertThrows(EmployeeNotFoundException.class, () -> underTestDepart.findMaxSalaryEmployee(1));
        assertEquals("Нет сотрудников в отделе " + 1, ex.getMessage());

        verify(underTest, times(1)).findAll();
    }

    @Test
    void findMaxSalaryEmployee_MaxSalary() {
        List<Employee> allPeople = new ArrayList<>();

        allPeople.add(Max);
        allPeople.add(Vlad);
        allPeople.add(Ivan);

        when(underTest.findAll())
                .thenReturn(allPeople);

        Employee result = underTestDepart.findMaxSalaryEmployee(1);
        assertEquals(Max, result);

        verify(underTest, times(1)).findAll();
    }

    @Test
    void findMinSalaryEmployee_throwException() {
        List<Employee> allPeople = new ArrayList<>();

        when(underTest.findAll())
                .thenReturn(allPeople);

        EmployeeNotFoundException ex =
                assertThrows(EmployeeNotFoundException.class, () -> underTestDepart.findMinSalaryEmployee(1));
        assertEquals("Нет сотрудников в отделе " + 1, ex.getMessage());

        verify(underTest, times(1)).findAll();
    }

    @Test
    void findMinSalaryEmployee_MinSalary() {
        List<Employee> allPeople = new ArrayList<>();

        allPeople.add(Max);
        allPeople.add(Vlad);
        allPeople.add(Ivan);

        when(underTest.findAll())
                .thenReturn(allPeople);

        Employee result = underTestDepart.findMinSalaryEmployee(1);
        assertEquals(Ivan, result);

        verify(underTest, times(1)).findAll();
    }

    @Test
    void getAll_FindByDepartment() {
        List<Employee> allPeople = new ArrayList<>();

        allPeople.add(Max);
        allPeople.add(Vlad);
        allPeople.add(Ivan);

        when(underTest.findAll())
                .thenReturn(allPeople);

        List<Employee> expected = new ArrayList<>();
        expected.add(Max);
        expected.add(Ivan);

        Collection<Employee> result = underTestDepart.getAll(1);
        assertEquals(expected, result);

        verify(underTest, times(1)).findAll();
    }

    @Test
    void getAllGropingByDepartment_FindAll() {
        List<Employee> allPeople = new ArrayList<>();

        allPeople.add(Max);
        allPeople.add(Vlad);
        allPeople.add(Ivan);

        when(underTest.findAll())
                .thenReturn(allPeople);

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

        verify(underTest, times(1)).findAll();
    }
}