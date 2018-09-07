package com.example.demo.repository;

import com.example.demo.movies.Trailer;
import org.springframework.data.repository.CrudRepository;

public interface ITrailerRepository extends CrudRepository<Trailer, Long>{
}
