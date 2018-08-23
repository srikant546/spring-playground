package com.example.demo.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@TestPropertySource(properties = {

        "wordCount.caseSensitive=true",
        "wordCount.words.skip[0]=in",
        "wordCount.words.skip[1]=an",
        "wordCount.words.skip[2]=a"
})
@SpringBootTest
public class WordCountConfigTest {

    @Autowired
    WordCountConfig wordCountConfig;

     @Test
    public void testWordCountConfig_with_testProperties_then_return_testProperties () {

         assertThat(wordCountConfig.getWords().getSkip(), contains("in", "an", "a"));
         assertTrue(wordCountConfig.isCaseSensitive());
     }


}