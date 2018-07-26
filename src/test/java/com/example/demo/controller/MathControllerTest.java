package com.example.demo.controller;

import com.example.demo.controller.PagesController;
import com.example.demo.service.MathService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import java.awt.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest({ MathController.class, MathService.class } )
public class MathControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetPi() throws Exception {

        RequestBuilder request = get("/math/pi");

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("3.141592653589793"));

    }

    @Test
    public void testAddCalculate() throws Exception {
        RequestBuilder request = get("/math/calculate?operation=add&x=4&y=6");

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 6 = 10"));
    }

    @Test
    public void testMultiplyCalculate() throws Exception {
        RequestBuilder request = get("/math/calculate?operation=multiply&x=4&y=6");

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("4 * 6 = 24"));
    }

    @Test
    public void testSubtractCalculate() throws Exception {
        RequestBuilder request = get("/math/calculate?operation=subtract&x=4&y=6");

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("4 - 6 = -2"));
    }


    @Test
    public void testDivideCalculate() throws Exception {
        RequestBuilder request = get("/math/calculate?operation=divide&x=30&y=5");

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("30 / 5 = 6"));
    }

    @Test
    public void testDefaultCalculate() throws Exception {
        RequestBuilder request = get("/math/calculate?x=30&y=5");

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("30 + 5 = 35"));
    }


    @Test
    public void testSum() throws Exception {
        RequestBuilder request = post("/math/sum?n=4&n=5&n=6");

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 5 + 6 = 15"));
    }

    @Test
    public void testVolumePost() throws Exception {
        RequestBuilder request = post("/math/volume/3/4/5");

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 3x4x5 rectangle is 60"));

    }

    @Test
    public void testVolumePatch() throws Exception {
        RequestBuilder request = patch("/math/volume/6/7/8");

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 6x7x8 rectangle is 336"));

    }

    @Test
    public void testAreaValidCircle() throws Exception {
        MockHttpServletRequestBuilder request = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", "circle")
                .param("radius", "4");

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Area of a circle with a radius of 4 is 50.26548"));

    }

    @Test
    public void testAreadValidRectangle() throws  Exception {
        MockHttpServletRequestBuilder request = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", "rectangle")
                .param("width", "4")
                .param("height", "7");

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Area of a 4x7 rectangle is 28"));

    }

    @Test
    public void testAreadInvalidRectangle() throws  Exception {
        MockHttpServletRequestBuilder request = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", "rectangle")
                .param("radius", "5");

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Invalid"));

    }


}