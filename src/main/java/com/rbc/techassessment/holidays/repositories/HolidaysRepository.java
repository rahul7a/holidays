package com.rbc.techassessment.holidays.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rbc.techassessment.holidays.entities.Holidays;

@Repository
public interface HolidaysRepository extends JpaRepository<Holidays, Long> {

    List<Holidays> findByCountry(String country);

}
