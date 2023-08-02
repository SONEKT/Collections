package com.example.colecctions.controller;

import com.example.colecctions.dto.Employee;
import com.example.colecctions.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee findMaxSalaryEmployee(@RequestParam int department) {
        return departmentService.findMaxSalaryEmployee(department);
    }

    @GetMapping("/min-salary")
    public Employee findMinSalaryEmployee(@RequestParam int department) {
        return departmentService.findMinSalaryEmployee(department);
    }

    @GetMapping("/all")
    public Collection<Employee> getAll(@RequestParam int department) {
        return departmentService.getAll(department);
    }

    @GetMapping("/allall")
    public Map<Integer, List<Employee>> getAllByDepartment() {
        return departmentService.getAllGropingByDepartment();
    }
}
