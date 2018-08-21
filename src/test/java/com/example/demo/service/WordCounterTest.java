package com.example.demo.service;

import com.example.demo.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class WordCounterTest {

    @Autowired
    AppConfig appConfig;

    @Test
    public void count() throws Exception {
        assertTrue("Did not work",appConfig.wordCounter().count("hello world").size() == 2);
    }

}