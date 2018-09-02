package com.example.demo.service;

import com.example.demo.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@TestPropertySource(properties = {

        "wordCount.caseSensitive=true",
        "wordCount.words.skip[0]=in",
        "wordCount.words.skip[1]=an",
        "wordCount.words.skip[2]=a"
})
@SpringBootTest
public class WordCounterTest {

    @Autowired
    AppConfig appConfig;

    @Test
    public void count() throws Exception {
        assertTrue("Did not work",appConfig.wordCounter().count("hello world").size() == 2);
    }

}