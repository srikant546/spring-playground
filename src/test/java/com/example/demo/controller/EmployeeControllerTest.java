package com.example.demo.controller;

import com.example.demo.config.SecurityConfig;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
@Import(SecurityConfig.class)
public class EmployeeControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    EmployeeRepository employeeRepository;

    @MockBean
    EmployeeService employeeService;

    @Test
    public void when_Manager_return_200() throws Exception {

        //Given
        RequestBuilder requestBuilder = get("/admin/employees").with(user("admin").roles("ADMIN"));

        //When
        this.mvc.perform(requestBuilder)
                // Then
                .andExpect(status().isOk());

    }

    @Test
    public void when_User_return_403() throws Exception {

        //Given
        RequestBuilder requestBuilder = get("/admin/employees").with(user("user").roles("USER"));

        //When
        this.mvc.perform(requestBuilder)
                // Then
                .andExpect(status().isForbidden());

    }

    @Test
    @WithAnonymousUser
    public void when_Anonymus_return_403() throws Exception {

        //Given
        RequestBuilder requestBuilder = get("/admin/employees");

        //When
        this.mvc.perform(requestBuilder)
                // Then
                .andExpect(status().isUnauthorized());

    }


//    @Test
//    @WithAnonymousUser
//    public void when_Anonymus_return_200() throws Exception {
//
//        //Given
//        RequestBuilder requestBuilder = get("employees");
//
//        //When
//        this.mvc.perform(requestBuilder)
//                // Then
//                .andExpect(status().isOk());
//
//    }

}