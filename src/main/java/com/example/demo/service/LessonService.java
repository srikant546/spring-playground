package com.example.demo.service;

import com.example.demo.entities.Lesson;
import com.example.demo.repository.LessonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonService {

    private LessonRepository lessonRepository;

    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public LessonService() {
    }

    public Iterable<Lesson> findAll() {
        return this.lessonRepository.findAll();
    }

    public Lesson save(Lesson lesson) {
        return this.lessonRepository.save(lesson);
    }

    public Optional<Lesson> findById(long id) {
        return this.lessonRepository.findById(id);
    }

    public void deleteById(long id) {
        this.lessonRepository.deleteById(id);
    }

    public Lesson findByTitle(String title) {
        return this.lessonRepository.findByTitle(title);
    }

    public List<Lesson> findByDate(String date1, String date2) {
        return this.findByDate(date1, date2);
    }
}
