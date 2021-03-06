package com.example.demo.controller;

import com.example.demo.entities.Lesson;
import com.example.demo.repository.LessonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.transaction.Transactional;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class LessonControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    LessonRepository repository;

    @Test
    @Transactional
    @Rollback
    public void create() throws Exception {

        String json = "{\n" +
                "        \"id\": 1,\n" +
                "        \"title\": \"Requests and Responses\"\n" +
                "    }";

        MockHttpServletRequestBuilder request = post("/lessons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));

    }

    @Test
    @Transactional
    @Rollback
    public void update() throws Exception {

        String json = "{\n" +
                "        \"title\": \"Requests and Responses\"\n" +
                "    }";

        MockHttpServletRequestBuilder request = patch("/lessons/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", equalTo("Requests and Responses")));

    }

    @Test
    @Transactional
    @Rollback
    public void getById() throws Exception {

        Lesson lesson = new Lesson();
        lesson.setId(1l);
        lesson.setTitle("hello");

        repository.save(lesson);

        MockHttpServletRequestBuilder request = get("/lessons")
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", equalTo(lesson.getId().intValue()) ));

    }

    @Test
    @Transactional
    @Rollback
    public void two_dates_return_lessons_list() throws Exception {

        MockHttpServletRequestBuilder request = get("/lessons/between?date1=2014-01-01&date2=2017-12-31")
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", equalTo(2) ));

    }

    @Test
    @Transactional
    @Rollback
    public void find_lesson_returnLesson() throws Exception {

        MockHttpServletRequestBuilder request = get("/lessons/find/SQL")
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(2) ));

    }

}