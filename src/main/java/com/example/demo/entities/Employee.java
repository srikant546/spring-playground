package com.example.demo.entities;

import com.example.demo.views.Views;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.publicView.class)
    private Long id;

    @JsonView(Views.publicView.class)
    private String name;

    @JsonView(Views.privateView.class)
    private int salary;

    @JsonView(Views.publicView.class)
    private Long managerId;

    @JsonView(Views.publicView.class)
    private String username;

    @JsonIgnore
    private String password;

    @JsonView(Views.publicView.class)
    private String role;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.role));
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
