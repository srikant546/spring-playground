package com.example.demo.controller;

import com.example.demo.service.WordCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class StringsController {

    @Autowired
    WordCounter wordCounter;

    @RequestMapping("/words/count")
    public Map<String, Integer> count (@RequestBody String words) {
        return wordCounter.count(words);
    }


}
