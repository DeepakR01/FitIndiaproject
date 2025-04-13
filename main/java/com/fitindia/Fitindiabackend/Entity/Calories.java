package com.fitindia.Fitindiabackend.Entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
@Builder
public class Calories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private Long Calories;
    private Long Protein;
    private Long Carbs;
    private Long Fats;

    // public Calories(Long id, Long calories, Long protien, Long carbs, Long fats)
    // {
    // // this.id = id;
    // this.id = id;
    // this.Calories = calories;
    // this.Protien = protien;
    // this.Carbs = carbs;
    // this.Fats = fats;
    // }

    public Calories(String date, Long calories, Long protein, Long carbs, Long fats) {
        // this.id = id;
        this.date = date;
        this.Calories = calories;
        this.Protein = protein;
        this.Carbs = carbs;
        this.Fats = fats;
    }

    public Calories() {
    }

    @OneToOne
    private User user;
}
