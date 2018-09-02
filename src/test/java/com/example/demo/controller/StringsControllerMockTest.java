package com.example.demo.controller;

import com.example.demo.config.AppConfig;
import com.example.demo.service.WordCounter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StringsController.class)
@AutoConfigureMockMvc(secure=false)
@ActiveProfiles("test")
public class StringsControllerMockTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AppConfig appConfig;

    @MockBean
    WordCounter wordCounter;

    @Test
    public void countWordsMockService() throws Exception {

        Map<String, Integer> result = new HashMap<>();
        result.put("the", 1);

        when(appConfig.wordCounter()).thenReturn(wordCounter);
        when(wordCounter.count("sboot")).thenReturn(result);

        MockHttpServletRequestBuilder request = post("/words/count")
                .contentType(MediaType.TEXT_PLAIN)
                .content("sboot");

        mockMvc.perform(request)
                .andExpect(status().isOk());
                //.andExpect(jsonPath("$.the", equalTo(1)));

    }

}
