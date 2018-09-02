package com.example.demo.entities;

import com.example.demo.views.Views;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.privateView.class)
    private Long id;

    @JsonView(Views.privateView.class)
    private String title;

    @Column(columnDefinition = "date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonView(Views.publicView.class)
    private Date deliveredOn;

    @JsonView(Views.privateView.class)
    public Long getId() {
        return id;
    }

    @JsonView(Views.privateView.class)
    public void setId(Long id) {
        this.id = id;
    }

    @JsonView(Views.privateView.class)
    public String getTitle() {
        return title;
    }

    @JsonView(Views.privateView.class)
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonView(Views.publicView.class)
    public Date getDeliveredOn() {
        return deliveredOn;
    }

    @JsonView(Views.privateView.class)
    public void setDeliveredOn(Date deliveredOn) {
        this.deliveredOn = deliveredOn;
    }
}



