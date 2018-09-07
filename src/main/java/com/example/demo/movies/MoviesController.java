package com.example.demo.movies;

import com.example.demo.repository.IMovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MoviesController {

//    @Autowired
//    MovieService movieService;
//
//    @RequestMapping("/movies")
//    public Object getMovies(@RequestParam String q) {
//        return movieService.getByMovieName(q);
//    }
//
//    @GetMapping("")
//    public Iterable<Movie> all() {
//        return this.repository.findAll();
//    }



    private final IMovieRepository repository;

    public MoviesController(IMovieRepository repository) {
        this.repository = repository;
    }


    @GetMapping("")
    public Iterable<MovieResource> all() {

        final List < MovieResource > collection = new ArrayList<>();
        repository.findAll().forEach(m -> collection.add(new MovieResource(m)));

        return collection;
    }

    @GetMapping("/{id}")
    public ResponseEntity< MovieResource > get(@PathVariable final long id) {

            Movie movie = this.repository.findById(id).orElse(null);
            MovieResource resource = new MovieResource(movie);
            return new ResponseEntity<>(resource, HttpStatus.OK);
        }

}
