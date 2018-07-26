package com.example.demo.controller;

import com.example.demo.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class PagesController {

    @Autowired
    MathService mathService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}
