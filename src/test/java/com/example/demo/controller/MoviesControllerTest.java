package com.example.demo.controller;

import com.example.demo.service.MovieService;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.springframework.test.web.client.ExpectedCount.manyTimes;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static sun.jvm.hotspot.runtime.VMOps.Verify;


@WebMvcTest({MovieService.class, MoviesController.class})
@AutoConfigureMockMvc(secure=false)
public class MoviesControllerTest {

    MovieService movieService = new MovieService();

    @Test
    public void getMovies() throws Exception {

    RestTemplate restTemplate = movieService.getRestTemplate();
    MockRestServiceServer server = MockRestServiceServer.createServer(restTemplate);

    server.expect(requestTo("http://www.omdbapi.com/?apikey=d349c827&s=harry"))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withSuccess("{\"Search\": [ {\"Title\": \"Harry Potter and the Deathly Hallows: Part 2\"}]}", MediaType.APPLICATION_JSON));

    Object o = movieService.getByMovieName("harry");

    assertThat(o.toString(), equalTo("[{Title=Harry Potter and the Deathly Hallows: Part 2}]"));


    server.verify();


    }

}