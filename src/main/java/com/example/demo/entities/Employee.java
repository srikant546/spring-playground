package com.example.demo.entities;

import com.example.demo.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

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
}
