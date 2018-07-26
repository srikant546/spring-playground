package com.example.demo.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;

@Service
public class MathService {

    public String getPi() {
        return "3.141592653589793";
    }

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

    public String area(String type, int radius, int width, int height) {

        DecimalFormat fiveDForm = new DecimalFormat("#.00000");

        switch (type) {
            case "circle":
             return radius == 0 ? "Invalid" : String.format("Area of a %s with a radius of %d is %s",
                    type,
                     radius,
                    (fiveDForm.format((Double.parseDouble(getPi())) * (radius * radius))));
            case "rectangle":
              return (width == 0 || height == 0) ? "Invalid" : String.format("Area of a %dx%d %s is %d",
                      width,
                      height,
                      type,
                      (height * width));
            default:
                return null;
        }
    }

}
