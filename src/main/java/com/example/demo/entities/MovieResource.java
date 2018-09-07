package com.example.demo.entities;


import com.example.demo.controller.MoviesController;
import com.example.demo.controller.TrailerController;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieResource extends ResourceSupport{

    private final Movie movie;

    @JsonCreator
    public MovieResource(Movie movie) {
        this.movie = movie;
        this.add(linkTo(methodOn(MoviesController.class).get(movie.getMovieId())).withSelfRel());
        this.add(linkTo(methodOn(TrailerController.class).findTrailer(movie.getMovieId())).withRel("trailer"));

    }

    public Movie getMovie() {
        return movie;
    }
}
