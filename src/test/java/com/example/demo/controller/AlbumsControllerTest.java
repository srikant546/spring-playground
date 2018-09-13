package com.example.demo.controller;


import com.example.demo.config.SecurityConfig;
import com.example.demo.entities.Album;
import com.example.demo.repository.AlbumsRepository;
import com.example.demo.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AlbumsController.class)
@Import(SecurityConfig.class)
public class AlbumsControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    AlbumsRepository repository;

    @MockBean
    EmployeeService employeeService;

    @Test
    public void when_get_then_return_mock_response() throws Exception {

        Album album = new Album();
        album.setId(1l);
        album.setName("hello");
        List<Album> albums = new ArrayList<>();

        albums.add(album);

        when(repository.findAll()).thenReturn(albums);

        RequestBuilder requestBuilder = get("/albums");

        this.mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", equalTo(1)));



        verify(repository, times(1)).findAll();


    }


    @Test
    public void when_post_then_return_mock_response() throws Exception {

        Album album = new Album();
        album.setId(1l);
        album.setName("Requests and Responses");

        String json = "{\n" +
                "        \"title\": \"Requests and Responses\"\n" +
                "    }";

        when(repository.save(album)).thenReturn(album);

        MockHttpServletRequestBuilder requestBuilder = post("/albums")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(requestBuilder)
                .andExpect(status().isOk());

    }

}
