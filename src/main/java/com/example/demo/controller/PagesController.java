package com.example.demo.controller;

import com.example.demo.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PagesController {

    @Autowired
    MathService mathService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}
