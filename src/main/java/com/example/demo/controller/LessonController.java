package com.example.demo.controller;

import com.example.demo.entities.Lesson;
import com.example.demo.repository.LessonRepository;
import com.example.demo.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/lessons")
public class LessonController {

    private final LessonRepository repository;

    public LessonController(LessonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public Iterable<Lesson> all() {
        return this.repository.findAll();
    }

    @PostMapping("")
    public Lesson create(@RequestBody Lesson lesson) {
        return this.repository.save(lesson);
    }

    @GetMapping("/{id}")
    @JsonView(Views.privateView.class)
    public Optional<Lesson> getById(@PathVariable long id) { return this.repository.findById(id); }

    @DeleteMapping("/{id}")
    public void deleteById (@PathVariable long id) { this.repository.deleteById(id); }

    @PatchMapping("/{id}")
    public Lesson updateById (@PathVariable long id, @RequestBody Lesson lesson) {

        Optional<Lesson> optionalLesson = this.repository.findById(id);

        if (optionalLesson.isPresent()) {
            Lesson lesson1 = optionalLesson.get();

            lesson1.setTitle(lesson.getTitle());
            lesson1.setDeliveredOn(lesson.getDeliveredOn());

            return this.repository.save(lesson1);
        }

        return null;
    }
}
