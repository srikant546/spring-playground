package com.example.demo.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {MathService.class})
public class MathServiceTest {


    @Autowired
    MathService mathService;

    @Test
    public void testMathServiceSubstract()
    {
        assertTrue("MathService.substract did not work", mathService.substract(4, 6).equals("4 - 6 = -2"));
    }

    @Test
    public void testMathServiceSum()
    {
        assertTrue("MathService.sum did not work", mathService.sum(asList("4", "5", "6")).equals("4 + 5 + 6 = 15"));
    }

    @Test
    public void testMathServiceAdd()
    {
        assertTrue("MathService.sum did not work", mathService.sum(asList("4", "5", "6")).equals("4 + 5 + 6 = 15"));
    }

    @Test
    public void testMathServiceMultiply()
    {
        assertTrue("MathService.multiply did not work", mathService.multiply(4, 5).equals("4 * 5 = 20"));
    }

    @Test
    public void testMathServiceDevide()
    {
        assertTrue("MathService.devide did not work", mathService.divide(6, 3).equals("6 / 3 = 2"));
    }

    @Test
    public void testMathServiceVolume()
    {
        assertTrue("MathService.volume did not work", mathService.volume(6, 7, 8).equals("The volume of a 6x7x8 rectangle is 336"));
    }

}
