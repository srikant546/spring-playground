package com.example.demo.service;

import com.example.demo.entities.Flight;
import org.springframework.stereotype.Component;

import java.util.*;

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


    public Map<String, Integer> getTotal(Flight flight) {

        List<Flight.Ticket> tickets = flight.getTickets();
        int total = 0;

        HashMap<String, Integer> result = new HashMap<>();

        for (Flight.Ticket t: tickets) {
            total = total + t.getPrice();
        }

        result.put("result", total);

        return result;

    }
}



