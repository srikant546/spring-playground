package com.example.demo.controller;

import com.example.demo.entities.Flight;


import com.example.demo.service.FlightService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    FlightService flightService;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping
    public List<Flight> getFlights() {
        return flightService.getFlights();
    }

    @GetMapping("/flight")
    public Flight getFlight() {
        return flightService.getFlight();
    }

    @PostMapping("/tickets/total")
    public Map<String, Integer> flightTotal(@RequestBody String flight) throws IOException{
        return flightService.getTotal(objectMapper.readValue(flight, Flight.class));
    }
}
