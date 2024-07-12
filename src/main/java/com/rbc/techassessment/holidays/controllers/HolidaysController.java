package com.rbc.techassessment.holidays.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rbc.techassessment.holidays.entities.Holidays;
import com.rbc.techassessment.holidays.services.HolidaysService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/holidays")
@Tag(name = "Holiday Management", description = "API for managing federal holidays")
public class HolidaysController {

    private HolidaysService holidaysService;

    public HolidaysController(HolidaysService holidaysService) {
        this.holidaysService = holidaysService;
    }

    @GetMapping
    @Operation(summary = "Get all Holidays", description = "Retrieve all holidays")
    public List<Holidays> retriveAllHolidays() {
        return holidaysService.fetchAllHolidays();
    }

    @GetMapping(value = "/{country}")
    @Operation(summary = "Get Holidays by Country", description = "Retrieve all holidays for a specific country")
    public List<Holidays> retriveHolidays(@PathVariable String country) {
        return holidaysService.fetchHolidaysByCountry(country);
    }

    @PostMapping
    @Operation(summary = "Add a new Holiday", description = "Add a new holiday to the system")
    public Holidays addHoliday(@RequestBody Holidays holiday) {
        return holidaysService.addHoliday(holiday);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Update an existing Holiday", description = "Update the details of an existing holiday")
    public Holidays updateHolidays(@PathVariable Long id, @RequestBody Holidays holiday) {
        return holidaysService.updateHoliday(id, holiday);
    }

}
