package com.example.demo.config;


import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    EmployeeService employeeService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().mvcMatchers("/flights/**", "/math/**", "/lessons/**").permitAll();
        http.authorizeRequests().mvcMatchers("/admin/**").hasRole("ADMIN");
        http.authorizeRequests().anyRequest().authenticated();
    }

    @Autowired
    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(employeeService);
    }
}
