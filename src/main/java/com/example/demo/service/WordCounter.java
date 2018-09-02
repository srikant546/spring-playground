package com.example.demo.service;

import com.example.demo.config.WordCountConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class WordCounter {

    @Autowired
    WordCountConfig wordCountConfig;

    public Map<String, Integer> count (String string) {

        return Arrays
                .asList(string.replaceAll("\\p{Punct}", "").split(" "))
                .stream()
                .map(s -> {
                    if (!wordCountConfig.isCaseSensitive())
                        return s.toLowerCase();
                    return s;
                })
                .filter(s -> !wordCountConfig.getWords().getSkip().contains(s))
                .collect(Collectors.groupingBy(String::valueOf, Collectors.summingInt(s -> 1)));

    }
}
