package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date departs;

    private List<Ticket> tickets;

    public Date getDeparts() {
        return departs;
    }

    @JsonProperty("tickets")
    public List<Ticket> getTickets() {
        return tickets;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    public static class Ticket {

        private Person passenger;
        private int price;

        public Person getPassenger() {
            return passenger;
        }

        public int getPrice() {
            return price;
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Person {
        private String firstName;
        private String lastName;

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

    }
}
