package com.example.demo.controller;

import com.example.demo.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/math")
public class MathController {

    @Autowired
    MathService mathService;

    @GetMapping("/calculate")
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

    @PostMapping("/sum")
    public String sum (@RequestParam MultiValueMap<String, String> n) {

        List<String> numbers = n.get("n");

        return mathService.sum(numbers);
    }

    @RequestMapping("/volume/{length}/{height}/{breath}")
    public String volume (@PathVariable int length, @PathVariable int height,  @PathVariable int breath) {
        return mathService.volume(length, height, breath);
    }

    @GetMapping("/pi")
    public String getPi() {
        return mathService.getPi();
    }

    @PostMapping(value = "/area", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String volume (@RequestParam String type, @RequestParam(defaultValue = "0") int radius,
                          @RequestParam(defaultValue = "0") int width, @RequestParam(defaultValue = "0") int height) {
        return mathService.area(type, radius, width, height);
    }

}
