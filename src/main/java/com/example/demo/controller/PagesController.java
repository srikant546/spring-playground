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

    @GetMapping("/math/pi")
    public double getPi() {
        return 3.141592653589793;
    }

    @GetMapping("/math/calculate")
    public String calculate( @RequestParam(value = "operation", defaultValue = "null") String operation,
                             @RequestParam("x") int x,
                             @RequestParam("y") int y) {

        switch (operation) {
            case "add":
                return mathService.sum(Arrays.asList(String.valueOf(x), String.valueOf(y)));
            case "subtract":
                return mathService.substract(x, y);
            case "multiply":
                return mathService.multiply(x, y);
            case "divide":
                return mathService.divide(x, y);
            case "null":
                return mathService.sum(Arrays.asList(String.valueOf(x), String.valueOf(y)));
            default:
                return mathService.sum(Arrays.asList(String.valueOf(x), String.valueOf(y)));
        }
    }

     @PostMapping("math/sum")
     public String sum (@RequestParam MultiValueMap<String, String> n) {

        List<String> numbers = n.get("n");

        return mathService.sum(numbers);
    }

}
