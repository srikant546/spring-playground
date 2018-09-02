package com.example.demo.controller;

import com.example.demo.entities.Lesson;
import com.example.demo.repository.LessonRepository;
import com.example.demo.service.LessonService;
import com.example.demo.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("/lessons")
public class LessonController {

    @Autowired
    LessonService repository;

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

    @GetMapping("/find/{title}")
    public Lesson findByTitle (@PathVariable String title) { return this.repository.findByTitle(title); }

    @GetMapping("/between")
    public List<Lesson> findLessonByDates (@RequestParam String date1, @RequestParam String date2) throws ParseException {

//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//
//            Date date = formatter.parse(date1);
//            Date dat = formatter.parse(date2);

            return this.repository.findByDate(date1, date2);

    }


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
