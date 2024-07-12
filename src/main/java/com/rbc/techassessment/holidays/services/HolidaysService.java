package com.rbc.techassessment.holidays.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rbc.techassessment.holidays.entities.Holidays;
import com.rbc.techassessment.holidays.repositories.HolidaysRepository;

@Service
public class HolidaysService {

    private HolidaysRepository holidaysRepository;

    public HolidaysService(HolidaysRepository holidaysRepository) {
        this.holidaysRepository = holidaysRepository;
    }

    public List<Holidays> fetchAllHolidays() {
        return holidaysRepository.findAll();
    }

    public List<Holidays> fetchHolidaysByCountry(String country) {
        List<Holidays> holidays = holidaysRepository.findByCountry(country);

        return holidays;
    }

    public Holidays addHoliday(Holidays holiday) {
        Holidays holidays = holidaysRepository.save(holiday);
        return holidays;
    }

    public Holidays updateHoliday(Long id, Holidays holiday) {
        Optional<Holidays> fetchedHolidays = holidaysRepository.findById(id);

        if (fetchedHolidays.isEmpty()) {
            // Nothing to update.
            throw new IllegalArgumentException(id + " is not found.");
        }

        Holidays fetchedHoliday = fetchedHolidays.get();
        fetchedHoliday.setCountry(holiday.getCountry());
        fetchedHoliday.setName(holiday.getName());
        fetchedHoliday.setOnDay(holiday.getOnDay());

        return holidaysRepository.save(fetchedHoliday);
    }

}
