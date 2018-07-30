package com.example.demo.controller;

import com.example.demo.controller.FlightController;
import com.example.demo.entities.Flight;
import com.example.demo.service.FlightService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest( {FlightService.class, FlightController.class, Flight.class, Flight.Ticket.class, Flight.Person.class})
public class FlightControllerTest {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

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

    @Test
    public void postTotalRawBody() throws Exception {
        String json = getJSON("/data.json");
        String result = "{\"result\":350}";

        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(json);

       this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(result));
    }

    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getFile())));
    }

    @Test
    public void getFlightTotalString() throws Exception {

        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(" {\n    \"tickets\": [\n      {\n        \"price\": 200\n      },\n      {\n        \"price\": 150\n      }\n    ]\n  }");

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(350)));

    }



    @Test
    public void getFlightTotalMapper() throws Exception {

        HashMap<String, Object> ticket1 = new HashMap<>();
        ticket1.put("price", 200);

        HashMap<String, Object> ticket2 = new HashMap<>();
        ticket2.put("price", 150);

        List<Map> tickets = Arrays.asList(ticket1, ticket2);

        HashMap<String, Object> data = new HashMap<>();

        data.put("tickets", tickets);

        RequestBuilder request = MockMvcRequestBuilders.post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(data));

        this.mockMvc
                .perform(request)
                .andExpect(jsonPath("$.result", is(350)));

    }

}