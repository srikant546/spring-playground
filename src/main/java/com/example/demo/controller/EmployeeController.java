package com.example.demo.controller;

import com.example.demo.entities.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/admin/employees")
    @JsonView(Views.privateView.class)
    public List<Employee> getAdminEmployees () {
        return ((List<Employee>) this.employeeRepository.findAll());
    }


    @GetMapping("/employees")
    @JsonView(Views.publicView.class)
    public List<Employee> getEmployees () {
        return ((List<Employee>) this.employeeRepository.findAll());
    }

    @GetMapping("/employees/me")
    @JsonView(Views.publicView.class)
    public Employee getEmployees (@AuthenticationPrincipal Employee employee) {
        return employee;
    }

}
