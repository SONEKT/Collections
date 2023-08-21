package com.example.colecctions.service;

import com.example.colecctions.dto.Employee;
import com.example.colecctions.exeptions.EmployeeAlreadyAddedException;
import com.example.colecctions.exeptions.EmployeeNotFoundException;
import com.example.colecctions.exeptions.EmployeeStorageFullException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {

    EmployeeServiceImpl underTest = new EmployeeServiceImpl();


    Employee employee = new Employee("IVAN", "IVANOV", 1, 100000);

    @Test
    void addEmployee_employeeIsNotInMap_employeeIsAdded() {

        Employee result = underTest.addEmployee(employee.getFirstName(), employee.getLastName(),
                employee.getDepartment(), employee.getSalary());
        assertEquals(employee, result);
    }

    @Test
    void addEmployee_employeeIsNInMap_throwException() {
        underTest.addEmployee(employee.getFirstName(), employee.getLastName(),
                employee.getDepartment(), employee.getSalary());

        EmployeeAlreadyAddedException ex =
                assertThrows(EmployeeAlreadyAddedException.class, () -> underTest.addEmployee(
                        employee.getFirstName(), employee.getLastName(),
                        employee.getDepartment(), employee.getSalary()));

        assertEquals("В коллекции уже есть такой сотрудник", ex.getMessage());
    }

    @Test
    void addEmployee_employeeIsTooMuch_throwException() {
        for (int i = 0; i < 10; i++) {
            char a = (char) (67 + i);

            underTest.addEmployee(employee.getFirstName() + a, employee.getLastName(),
                    employee.getDepartment(), 10000 + i);
        }

        EmployeeStorageFullException ex =
                assertThrows(EmployeeStorageFullException.class, () -> underTest.addEmployee(
                        employee.getFirstName(), employee.getLastName(),
                        employee.getDepartment(), employee.getSalary()));

        assertEquals("Превышен лимит сотрудников фирмы", ex.getMessage());
    }


    @Test
    void removeEmployee_employeeIsNotInMap_throwException() {
        EmployeeNotFoundException ex =
                assertThrows(EmployeeNotFoundException.class, () -> underTest.removeEmployee("Ivan", "Ivanov"));
        assertEquals("Данного сотрудника нет в коллекции", ex.getMessage());
    }

    @Test
    void removeEmployee_employeeIsInMap_employeeRemovedAndReturned() {
        underTest.addEmployee(employee.getFirstName(), employee.getLastName(), employee.getDepartment(), employee.getSalary());

        Employee result = underTest.removeEmployee(employee.getFirstName(), employee.getLastName());

        assertEquals(employee, result);
        assertFalse(underTest.findAll().contains(employee));
    }

    @Test
    void findEmployee_employeeIsInMap_returnEmployee() {
        underTest.addEmployee(employee.getFirstName(), employee.getLastName(),
                employee.getDepartment(), employee.getSalary());

        Employee result = underTest.findEmployee(employee.getFirstName(), employee.getLastName());
        assertEquals(employee, result);
    }

    @Test
    void findEmployee_employeeIsNotFound_throwsException() {
        EmployeeNotFoundException ex =
                assertThrows(EmployeeNotFoundException.class, () -> underTest.findEmployee(
                        employee.getFirstName(), employee.getLastName()));
        assertEquals("Сотрудник не найден", ex.getMessage());
    }

    @Test
    void findAll_employeesAreInMap_returnCollectionOfEmployees() {
        List<Employee> expected = new ArrayList<>();
        expected.add(employee);

        underTest.addEmployee(employee.getFirstName(), employee.getLastName(),
                employee.getDepartment(), employee.getSalary());

        Collection<Employee> result = underTest.findAll();
        assertIterableEquals(expected, result);
    }

    @Test
    void findAll_employeesNoInMap_returnCollectionOfEmployees() {
        List<Employee> expected = new ArrayList<>();

        Collection<Employee> result = underTest.findAll();

        assertIterableEquals(expected, result);
    }
}