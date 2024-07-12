package com.rbc.techassessment.holidays.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rbc.techassessment.holidays.entities.Holidays;
import com.rbc.techassessment.holidays.repositories.HolidaysRepository;

public class HolidayServicesTest {

    @InjectMocks
    private HolidaysService holidaysService;

    @Mock
    private HolidaysRepository holidaysRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetHolidaysByCountry() {
        Holidays holiday = new Holidays();
        holiday.setCountry("USA");
        holiday.setName("Independence Day");
        holiday.setOnDay("2024-07-04");

        when(holidaysRepository.findByCountry("USA")).thenReturn(Arrays.asList(holiday));

        List<Holidays> holidays = holidaysService.fetchHolidaysByCountry("USA");

        assertNotNull(holidays);
        assertEquals(1, holidays.size());
        assertEquals("USA", holidays.get(0).getCountry());
        assertEquals("Independence Day", holidays.get(0).getName());
    }

    @Test
    void testAddHoliday() {
        Holidays holiday = new Holidays();
        holiday.setCountry("USA");
        holiday.setName("Independence Day");
        holiday.setOnDay("2024-07-04");

        when(holidaysRepository.save(any(Holidays.class))).thenReturn(holiday);

        Holidays savedHoliday = holidaysService.addHoliday(holiday);

        assertNotNull(savedHoliday);
        assertEquals("USA", savedHoliday.getCountry());
        assertEquals("Independence Day", savedHoliday.getName());
    }

    @Test
    void testUpdateHoliday() {
        Holidays existingHoliday = new Holidays();
        existingHoliday.setId(1L);
        existingHoliday.setCountry("USA");
        existingHoliday.setName("Old Name");
        existingHoliday.setOnDay("2024-01-01");

        Holidays updatedHoliday = new Holidays();
        updatedHoliday.setCountry("USA");
        updatedHoliday.setName("New Year");
        updatedHoliday.setOnDay("2024-01-01");

        when(holidaysRepository.findById(1L)).thenReturn(Optional.of(existingHoliday));
        when(holidaysRepository.save(any(Holidays.class))).thenReturn(updatedHoliday);

        Holidays result = holidaysService.updateHoliday(1L, updatedHoliday);

        assertNotNull(result);
        assertEquals("New Year", result.getName());
    }

}
