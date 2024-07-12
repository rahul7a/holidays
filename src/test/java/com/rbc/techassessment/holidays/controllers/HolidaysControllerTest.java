package com.rbc.techassessment.holidays.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.rbc.techassessment.holidays.RbcSampleProjectHolidaysApplication;
import com.rbc.techassessment.holidays.entities.Holidays;
import com.rbc.techassessment.holidays.services.HolidaysService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = RbcSampleProjectHolidaysApplication.class)
@AutoConfigureMockMvc
public class HolidaysControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HolidaysService holidaysService;

    @Test
    public void testGetHolidays() throws Exception {
        Holidays holiday = new Holidays();
        holiday.setCountry("USA");
        holiday.setName("New Year Eve");
        holiday.setOnDay("2020-01-01");

        when(holidaysService.fetchHolidaysByCountry("USA")).thenReturn(Arrays.asList(holiday));

        mockMvc.perform(get("/v1/holidays/USA")).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].country").value("USA"))
                .andExpect(jsonPath("$[0].name").value("New Year Eve"));
    }

    @Test
    public void testAddHoliday() throws Exception {
        Holidays holiday = new Holidays();
        holiday.setCountry("USA");
        holiday.setName("Christmas");
        holiday.setOnDay("2024-12-25");

        when(holidaysService.addHoliday(any(Holidays.class))).thenReturn(holiday);

        mockMvc.perform(post("/v1/holidays").contentType(MediaType.APPLICATION_JSON)
                .content("{\"country\":\"USA\", \"name\":\"Christmas\", \"date\":\"2024-12-25\"}"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.country").value("USA"))
                .andExpect(jsonPath("$.name").value("Christmas"));
    }

    @Test
    public void testUpdateHoliday() throws Exception {
        Holidays holiday = new Holidays();
        holiday.setId(1L);
        holiday.setCountry("USA");
        holiday.setName("New Year Eve");
        holiday.setOnDay("2024-01-01");

        when(holidaysService.updateHoliday(eq(1L), any(Holidays.class))).thenReturn(holiday);

        mockMvc.perform(put("/v1/holidays/1").contentType(MediaType.APPLICATION_JSON)
                .content("{\"country\":\"USA\", \"name\":\"New Year Eve\", \"date\":\"2024-01-01\"}"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.country").value("USA"))
                .andExpect(jsonPath("$.name").value("New Year Eve"));
    }

}
