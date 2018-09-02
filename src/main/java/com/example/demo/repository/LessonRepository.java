package com.example.demo.repository;

import com.example.demo.entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long>{

    Lesson findByTitle(String title);

    @Query(value = "SELECT * FROM lessons where delivered_on between ? and ? ", nativeQuery = true)
    public List<Lesson> findByDate(String date1, String date2);
}
