package com.example.demo.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties("word-count")
public class WordCountConfig {

    private boolean caseSensitive;
    private Words words;

    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    public Words getWords() {
        return this.words;
    }

    public void setWords(Words words) {
        this.words = words;
    }

    public static class Words {
        private List<String> skip;

        public List<String> getSkip() {
            return this.skip;
        }

        public void setSkip(List<String> skip) {
            this.skip = skip;
        }
    }

}
