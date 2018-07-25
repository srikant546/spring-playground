package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PagesController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/math/pi")
    public double getPi() {
        return 3.141592653589793;
    }

}
