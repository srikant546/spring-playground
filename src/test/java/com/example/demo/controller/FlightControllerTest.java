package com.example.demo.controller;

import com.example.demo.controller.FlightController;
import com.example.demo.entities.Flight;
import com.example.demo.service.FlightService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest( {FlightService.class, FlightController.class, Flight.class})
public class FlightControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getFlightTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/flights/flight");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tickets[0].passenger.firstName", is("Some name")))
                .andExpect(jsonPath("$.tickets[0].passenger.lastName", is("Some other name")));
    }

    @Test
    public void getFlightsTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/flights");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].tickets[0].passenger.firstName", is("Some name")))
                .andExpect(jsonPath("$[1].tickets[0].passenger.firstName", is("Some other name")));
    }



}