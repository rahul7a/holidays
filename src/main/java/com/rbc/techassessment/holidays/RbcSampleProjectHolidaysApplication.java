package com.rbc.techassessment.holidays;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.rbc.techassessment.holidays.entities.Holidays;
import com.rbc.techassessment.holidays.repositories.HolidaysRepository;

@SpringBootApplication
public class RbcSampleProjectHolidaysApplication {

    @Autowired
    private HolidaysRepository holidaysRepository;

    public static void main(String[] args) {
        SpringApplication.run(RbcSampleProjectHolidaysApplication.class, args);
    }

    @Bean
    InitializingBean insertRecords() {
        return () -> {
            holidaysRepository.save(new Holidays("USA", "New Year Eve", "2020-01-01"));
            holidaysRepository.save(new Holidays("CANADA", "New Year eve", "2020-01-01"));
        };
    }

}
