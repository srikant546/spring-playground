package com.example.demo.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MathService {

    public String substract (int x, int y) {
        return  x + " - " + y + " = " + (x - y);
    }

    public String multiply (int x, int y) {
        return  x + " * " + y + " = " + (x * y);
    }

    public String divide (int x, int y) {
        return x + " / " + y + " = " + (x / y);
    }

    public String sum (List<String> n) {
        int total = 0;
        String output = String.join(" + ", n);
        for (String i: n) {
            total += Integer.parseInt(i);
        }
        return output + " = " + total;
    }

    public String volume (int l, int b, int h) {
        return "The volume of a " + l + "x" + b + "x" + h + " rectangle is " + (l*b*h);
    }
}
