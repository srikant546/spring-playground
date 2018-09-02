package com.example.demo.controller;

import com.example.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MoviesController {

    @Autowired
    MovieService movieService;

    @RequestMapping("/movies")
    public Object getMovies(@RequestParam String q) {
        return movieService.getByMovieName(q);
    }
}
