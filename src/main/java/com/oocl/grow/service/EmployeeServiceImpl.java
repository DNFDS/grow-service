package com.oocl.grow.service;

import com.oocl.grow.model.Employee;
import com.oocl.grow.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee getEmployeeByName(String name) {
        return employeeRepository.findByName(name);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
