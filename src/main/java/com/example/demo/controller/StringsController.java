package com.example.demo.controller;

import com.example.demo.config.AppConfig;
import com.example.demo.service.WordCounter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class StringsController {

    ApplicationContext factory = new AnnotationConfigApplicationContext(AppConfig.class);

    WordCounter wordCounter = factory.getBean(WordCounter.class);

    @RequestMapping("/words/count")
    public Map<String, Integer> count (@RequestBody String words, WordCounter wordCounter) {
        return wordCounter.count(words);
    }


}
