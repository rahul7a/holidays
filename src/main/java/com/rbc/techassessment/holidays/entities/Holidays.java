package com.rbc.techassessment.holidays.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "holidays")
public class Holidays {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Country cannot be empty")
    private String country;

    @NotEmpty(message = "Holiday name cannot be empty")
    private String name;

    @NotEmpty(message = "Holiday - day cannot be empty")
    private String onDay;

    public Holidays() {
    }

    public Holidays(String country, String name, String onDay) {
        this.country = country;
        this.name = name;
        this.onDay = onDay;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOnDay() {
        return onDay;
    }

    public void setOnDay(String onDay) {
        this.onDay = onDay;
    }

}
