package com.example.demo.service;

import com.example.demo.entities.Flight;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class FlightService {

    public Flight getFlight() {
        return  Flight.builder()
                .departs(new Date())
                .tickets(Arrays.asList(new Flight.Ticket(new Flight.Person("Some name", "Some other name"), 200)
                ))
                .build();

    }

    public List<Flight> getFlights() {

        return Arrays.asList(Flight.builder()
                        .departs(new Date())
                        .tickets(Arrays.asList(new Flight.Ticket(new Flight.Person("Some name", "Some other name"), 200)
                        ))
                        .build(),
                Flight.builder()
                        .departs(new Date())
                        .tickets(Arrays.asList(new Flight.Ticket(new Flight.Person("Some other name", null), 400)
                        ))
                        .build());
    }
}



