package com.fitindia.Fitindiabackend.Dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fitindia.Fitindiabackend.Entity.Calories;

public interface CaloriesDao extends JpaRepository<Calories, Long> {
    // public List<Calories> findByDate(String date);
}
