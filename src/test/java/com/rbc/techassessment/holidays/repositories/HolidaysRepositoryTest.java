package com.rbc.techassessment.holidays.repositories;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rbc.techassessment.holidays.entities.Holidays;

import jakarta.transaction.Transactional;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class HolidaysRepositoryTest {

    @Autowired
    private HolidaysRepository holidaysRepository;

    @Before
    public void setUp() {
        holidaysRepository.deleteAll();
    }

    @Test
    public void testFindByCountry() {
        // Arrange
        Holidays holiday1 = new Holidays();
        holiday1.setCountry("USA");
        holiday1.setName("Independence Day");
        holiday1.setOnDay("2020-07-04");
        holidaysRepository.save(holiday1);

        Holidays holiday2 = new Holidays();
        holiday2.setCountry("USA");
        holiday2.setName("New Year");
        holiday2.setOnDay("2020-01-01");
        holidaysRepository.save(holiday2);

        Holidays holiday3 = new Holidays();
        holiday3.setCountry("Canada");
        holiday3.setName("Canada Day");
        holiday3.setOnDay("2020-07-01");
        holidaysRepository.save(holiday3);

        // Act
        List<Holidays> usHolidays = holidaysRepository.findByCountry("USA");

        // Assert
        assertEquals(usHolidays.size(), 2);
    }

    @Test
    public void testSaveHoliday() {
        // Arrange
        Holidays holiday = new Holidays();
        holiday.setCountry("Canada");
        holiday.setName("Canada Day");
        holiday.setOnDay("2020-07-01");

        // Act
        Holidays savedHoliday = holidaysRepository.save(holiday);

        // Assert
        assertNotNull(savedHoliday);
        assertNotNull(savedHoliday.getId());
        assertEquals(savedHoliday.getCountry(), "Canada");
        assertEquals(savedHoliday.getName(), "Canada Day");
        assertEquals(savedHoliday.getOnDay(), "2020-07-01");
    }

    @Test
    public void testFindById() {
        // Arrange
        Holidays holiday = new Holidays();
        holiday.setCountry("USA");
        holiday.setName("Thanksgiving");
        holiday.setOnDay("2020-11-28");
        Holidays savedHoliday = holidaysRepository.save(holiday);

        // Act
        Holidays foundHoliday = holidaysRepository.findById(savedHoliday.getId()).orElse(null);

        // Assert
        assertNotNull(foundHoliday);
        assertEquals(foundHoliday.getId(), savedHoliday.getId());
        assertEquals(foundHoliday.getCountry(), "USA");
        assertEquals(foundHoliday.getName(), "Thanksgiving");
        assertEquals(foundHoliday.getOnDay(), "2020-11-28");
    }

}
