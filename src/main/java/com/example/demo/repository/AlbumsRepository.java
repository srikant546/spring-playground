package com.example.demo.repository;

import com.example.demo.entities.Album;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlbumsRepository extends CrudRepository<Album, Long>{

    List<Album> findAll();
}
