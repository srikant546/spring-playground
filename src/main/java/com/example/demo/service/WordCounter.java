package com.example.demo.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class WordCounter {

    public Map<String, Integer> count (String string) {
        Map<String, Integer> result = new LinkedHashMap<>();
        String[] split = string.replaceAll("\\p{Punct}", "").split( " " );

        for (String str: split) {

            if (result.containsKey(str)) {
                result.replace(str, (result.get(str)+1));
            } else {
                result.put(str, 1);
            }
        }

        return result;
    }
}
