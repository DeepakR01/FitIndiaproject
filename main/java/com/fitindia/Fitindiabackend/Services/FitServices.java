package com.fitindia.Fitindiabackend.Services;

import java.util.List;
import java.util.Map;

import com.fitindia.Fitindiabackend.Entity.Calories;

public interface FitServices {
    public List<Calories> getCalories();

    public Calories getCalories(Long id);

    public Calories addCalories(Calories calories);

    public Calories deleteCalories(Long id);

    public Long getTotalCalories();

    public Long getTotalFats();

    public Long getTotalProtien();

    public Long getTotalCarbs();

    public List<Calories> findByDate(String date);

    public String getdiet(String string);

    public String getchat(String string);

    String getDietRecommendation(Map<String, String> inputData);

    public void save(Calories newCalorie);

    public List<Calories> findAll();
}
