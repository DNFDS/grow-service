package com.oocl.grow.controller;

import com.oocl.grow.model.Employee;
import com.oocl.grow.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/goal")
public class GoalController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/get_all_goals")
    public List<Employee> getAllGoals() {
        return employeeService.getAllEmployees();
    }
}
