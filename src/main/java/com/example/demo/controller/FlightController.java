package com.example.demo.controller;

import com.example.demo.entities.Flight;


import com.example.demo.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    FlightService flightService;

    @GetMapping
    public List<Flight> getFlights() {
        return flightService.getFlights();
    }

    @GetMapping("/flight")
    public Flight getFlight() {
        return flightService.getFlight();
    }

}
