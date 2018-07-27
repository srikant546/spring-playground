package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public static class Ticket {

        private Person passenger;
        private int price;

        public Ticket(Person passangers, int price) {
            this.passenger = passangers;
            this.price = price;
        }

        public Person getPassenger() {
            return passenger;
        }

        public int getPrice() {
            return price;
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Person {
        private String firstName;
        private String lastName;

        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

    }
}
