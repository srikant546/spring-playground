package com.example.demo.controller;

import com.example.demo.entities.Album;
import com.example.demo.repository.AlbumsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumsController {

    private final AlbumsRepository repository;

    public AlbumsController(AlbumsRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public Iterable<Album> getAlbums() {
        return repository.findAll();
    }

    @PostMapping("")
    public Album createAlbums(Album album) {
        return repository.save(album);
    }

}
