package com.example.demo.service;
import com.example.demo.config.WordCountConfig;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WordCounter {

    @Autowired
    WordCountConfig wordCountConfig;

    public Map<String, Integer> count (String string) {
        Map<String, Integer> result = new LinkedHashMap<>();
        String[] split = string.replaceAll("\\p{Punct}", "").split( " " );
        List<String> skip = new ArrayList<>();
                skip = wordCountConfig.getWords().getSkip();

        for (String str: split) {

            if (!wordCountConfig.isCaseSensitive()) {
                str = str.toLowerCase();
            }

            if (result.containsKey(str)) {
                result.replace(str, (result.get(str)+1));
            } else {
                result.put(str, 1);
            }
        }

        for (String s: skip) {
            result.remove(s);
        }

        return result;
    }
}
