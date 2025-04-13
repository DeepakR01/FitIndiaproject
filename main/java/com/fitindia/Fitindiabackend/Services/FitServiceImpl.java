package com.fitindia.Fitindiabackend.Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fitindia.Fitindiabackend.Dao.CaloriesDao;
import com.fitindia.Fitindiabackend.Entity.Calories;

// import lombok.Value;

@Service
public class FitServiceImpl implements FitServices {

    // List<Calories> calories;
    @Autowired
    private CaloriesDao caloriesDao;

    @Value("${flask.api.url}")
    private String flaskApiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String getDietRecommendation(Map<String, String> inputData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(inputData, headers);

        Map<String, Object> response = restTemplate.postForObject(flaskApiUrl, request, Map.class);

        if (response != null && response.containsKey("response")) {
            return (String) response.get("response");
        } else {
            return "Failed to get diet recommendation.";
        }
    }

    public FitServiceImpl() {
        // calories = new ArrayList<>();
        // calories.add(new Calories(1L, 100L, 10L, 20L, 30L));
        // calories.add(new Calories(2L, 200L, 20L, 30L, 40L));
    }

    @Override
    public List<Calories> getCalories() {
        return caloriesDao.findAll();
    }

    @Override
    public Calories getCalories(Long id) {
        // return calories.stream().filter(e -> e.getId().equals(id)).findFirst().get();
        return caloriesDao.findById(id).get();
    }

    @Override
    public Calories addCalories(Calories calories) {
        // this.calories.add(calories);
        caloriesDao.save(calories);
        return calories;
    }

    @Override
    public Calories deleteCalories(Long id) {
        // calories.remove(id);
        caloriesDao.deleteById(id);
        return null;
    }

    @Override
    public Long getTotalCalories() {
        Long totalCalories = 0L;

        List<Calories> calories = caloriesDao.findAll();
        for (Calories c : calories) {
            totalCalories += c.getCalories();
        }
        return totalCalories;
    }

    @Override
    public Long getTotalFats() {
        Long totalFats = 0L;

        List<Calories> calories = caloriesDao.findAll();
        for (Calories c : calories) {
            totalFats += c.getFats();
        }
        return totalFats;
    }

    @Override
    public Long getTotalProtien() {
        Long totalProtien = 0L;

        List<Calories> calories = caloriesDao.findAll();
        for (Calories c : calories) {
            totalProtien += c.getProtein();
        }
        return totalProtien;
    }

    @Override
    public Long getTotalCarbs() {
        Long totalCarbs = 0L;

        List<Calories> calories = caloriesDao.findAll();
        for (Calories c : calories) {
            totalCarbs += c.getCarbs();
        }
        return totalCarbs;
    }

    @Override
    public List<Calories> findByDate(String Date) {
        List<Calories> calories = caloriesDao.findAll();
        List<Calories> result = new ArrayList<>();
        for (Calories c : calories) {
            if (c.getDate().equals(Date)) {
                result.add(c);
            }
        }
        return result;
    }

    @Override
    public String getdiet(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getdiet'");
    }

    @Override
    public String getchat(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getchat'");
    }

    @Override
    public String getDietRecommendation(com.fitindia.Fitindiabackend.Services.Map<String, String> inputData) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDietRecommendation'");
    }

    public List<Calories> findAll() {
        return caloriesDao.findAll();
    }

    @Override
    public void save(Calories newCalorie) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }
}
