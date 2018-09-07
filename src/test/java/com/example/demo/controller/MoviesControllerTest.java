package com.example.demo.controller;

import com.example.demo.config.SecurityConfig;
import com.example.demo.movies.Movie;
import com.example.demo.movies.MoviesController;
import com.example.demo.repository.IMovieRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(MoviesController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc(secure=false)
@EnableAutoConfiguration(exclude = SecurityConfig.class)
public class MoviesControllerTest {
    private ArrayList<Movie> movies;
    private Movie movie1;
    private Movie movie2;
    private Movie movie3;

    @Before
    public void setUp() throws Exception {
        movie1 = new Movie("Boss Baby");
        movie1.setMovieId(1L);
        movie2 = new Movie("Beauty and the Beast");
        movie2.setMovieId(2L);
        movie3 = new Movie("Logan");
        movie3.setMovieId(3L);
        movies = new ArrayList<>();
        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
    }

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IMovieRepository repo;

    @Test
    public void testHateoasIndexRoute() throws Exception {
        when(this.repo.findAll()).thenReturn(movies);

        MockHttpServletRequestBuilder request = get("/movies");

        this.mvc.perform(request)
                .andDo(print())
                .andExpect(jsonPath("$[0].links[0].href", is("http://localhost/movies/1")))
                .andExpect(jsonPath("$[0].links[1].href", is("http://localhost/trailers/1")))
                .andExpect(jsonPath("$[1].links[1].href", is("http://localhost/trailers/2")))
                .andExpect(jsonPath("$[2].links[0].href", is("http://localhost/movies/3")));
    }
}