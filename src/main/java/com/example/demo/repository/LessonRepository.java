package com.example.demo.repository;

import com.example.demo.entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long>{

}
