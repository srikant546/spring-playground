package com.example.demo.repository;

import com.example.demo.entities.Movie;
import org.springframework.data.repository.CrudRepository;

public interface IMovieRepository extends CrudRepository<Movie, Long>{
}
