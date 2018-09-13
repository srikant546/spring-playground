package com.example.demo.controller;

import com.example.demo.service.WordCounter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(secure=false)
@ActiveProfiles("test")
@TestPropertySource(properties = {
        "wordCount.caseSensitive=true",
        "wordCount.words.skip[0]=the",
        "wordCount.words.skip[1]=an",
        "wordCount.words.skip[2]=a"
})


public class StringsControllerRealClassTest {
    @Autowired
    StringsController stringsController;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private WordCounter wordCounter;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testWordCountWithOutMock() throws Exception {
        Map<String, Integer> map = new HashMap<>();
        map.put("hello CAP", 1);

        MockHttpServletRequestBuilder request = post("/words/count")
                .contentType(MediaType.TEXT_PLAIN)
                .content("hello CAP");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.hello", equalTo(1)))
                .andExpect(jsonPath("$.CAP", equalTo(1)));

    }
}