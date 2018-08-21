package com.example.demo.controller;

import com.example.demo.config.AppConfig;
import com.example.demo.service.WordCounter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StringsController.class)
public class StringsControllerTest {

    @Autowired
    StringsController stringsController;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AppConfig appConfig;


    @MockBean
    WordCounter wordCounter;

    @Test
    public void count() throws Exception {

        MockHttpServletRequestBuilder request = post("/words/count")
                .contentType(MediaType.TEXT_PLAIN)
                .content("hello world");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.hello", equalTo(1)));

    }

    @Test
    public void countWords() throws Exception {

        MockHttpServletRequestBuilder request = post("/words/count")
                .contentType(MediaType.TEXT_PLAIN)
                .content("hello world");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.hello", equalTo(1)));

    }


    @Test
    public void countWordsMockService() throws Exception {

        Map<String, Integer> result = new HashMap<>();

        result.put("hello", 1);

        when(wordCounter.count("hello")).thenReturn(result); 

        MockHttpServletRequestBuilder request = post("/words/count")
                .contentType(MediaType.TEXT_PLAIN)
                .content("hello");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.hello", equalTo(1)));

    }


}