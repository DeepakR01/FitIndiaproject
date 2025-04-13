package com.fitindia.Fitindiabackend.Controller;

import java.nio.channels.Pipe.SourceChannel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.fitindia.Fitindiabackend.Entity.Calories;
import com.fitindia.Fitindiabackend.Services.FitServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
// @Controller
public class FitController {

    @Autowired
    private FitServices caloriesService;

    @RequestMapping("/home")
    // @ResponseBody
    public ModelAndView home(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Home.html");
        return modelAndView;
    }

    @GetMapping("/dashboard")
    // @ResponseBody
    public ModelAndView dashboard(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        int totalCalories = caloriesService.getTotalCalories().intValue();
        int totalFats = caloriesService.getTotalFats().intValue();
        int totalProtien = caloriesService.getTotalProtien().intValue();
        int totalCarbs = caloriesService.getTotalCarbs().intValue();
        model.addAttribute("totalFats", totalFats);
        model.addAttribute("totalProtien", totalProtien);
        model.addAttribute("totalCarbs", totalCarbs);
        model.addAttribute("totalCalories", totalCalories);
        modelAndView.setViewName("Dashboard.html");
        return modelAndView;
    }

    @RequestMapping("/signup")
    // @ResponseBody
    public ModelAndView signup(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Signup.html");
        return modelAndView;
    }

    @RequestMapping("/login")
    // @ResponseBody
    public ModelAndView login(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Login.html");
        return modelAndView;
    }

    @RequestMapping("/dietplan")
    // @ResponseBody
    public ModelAndView dietplan(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.html");
        return modelAndView;
    }

    @RequestMapping("/caloriecalculator")
    // @ResponseBody
    public ModelAndView caloriecalculator(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Caloriecalculator.html");
        return modelAndView;
    }

    @RequestMapping("/chat")
    // @ResponseBody
    public ModelAndView chatbot(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("chat.html");
        return modelAndView;
    }

    // getting calories
    @GetMapping("/calories")
    public List<Calories> getMethodName() {
        return caloriesService.getCalories();
    }

    // getting calories by id
    @GetMapping("/calories/{id}")
    public Calories getMethodName(@PathVariable String id) {
        return caloriesService.getCalories(Long.parseLong(id));
    }

    // @PostMapping("/add")
    // public Calories postMethodName(@RequestBody Calories calories) {
    // caloriesService.addCalories(calories);
    // return "redirect:/";
    // }

    @PostMapping("/add")
    public ModelAndView add(@RequestParam("date") String date,
            @RequestParam("calories") Long calories,
            @RequestParam("fats") Long fats, @RequestParam("carbs") Long carbs,
            @RequestParam("protein") Long protein) {
        Calories newCalories = new Calories();
        newCalories.setDate(date);
        newCalories.setCalories(calories);
        newCalories.setFats(fats);
        newCalories.setCarbs(carbs);
        newCalories.setProtein(protein);

        caloriesService.addCalories(newCalories);

        return new ModelAndView("redirect:/dashboard");
    }

    // @PostMapping("/add")
    // public ModelAndView add(@RequestParam("date") String
    // date,@RequestParam("calories") Long calories,@RequestParam("fats") Long
    // fats,@RequestParam("carbs") Long carbs,@RequestParam("protein") Long protein)
    // {

    // Calories newCalories = new Calories();

    // try {
    // DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    // // or dd-MM-yyyy, based on // your form input
    // LocalDate localDate = LocalDate.parse(date, inputFormatter);
    // newCalories.setDate(localDate); // setDate now expects a LocalDate object
    // } catch (DateTimeParseException e) {
    // // Handle invalid date format (e.g., log the error, show an error message)
    // System.err.println("Invalid date format: " + e.getMessage());
    // // You might want to return an error view instead of redirecting
    // return new ModelAndView("redirect:/dashboard"); // or return an error view.
    // }

    // newCalories.setCalories(calories);
    // newCalories.setFats(fats);
    // newCalories.setCarbs(carbs);
    // newCalories.setProtein(protein);

    // caloriesService.addCalories(newCalories);

    // return new ModelAndView("redirect:/dashboard");
    // }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCalories(@PathVariable String id) {
        try {
            caloriesService.deleteCalories(Long.parseLong(id));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/totalcalories")
    public Long getTotalCalories() {
        return caloriesService.getTotalCalories();
    }

    @PostMapping("/diet")
    public String postMethodName(@RequestBody String entity) {
        // TODO: process POST request

        return entity;
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam("date") String date, Model model) {
        List<Calories> caloriesList = caloriesService.findByDate(new StringBuilder(date).reverse().toString());
        model.addAttribute("caloriesList", caloriesList);
        // ... other logic...
        return new ModelAndView("redirect:/dashboard");
    }

    @PostMapping("/getDiet")
    public String getDiet(
            @RequestParam("height") String height,
            @RequestParam("weight") String weight,
            @RequestParam("age") String age,
            @RequestParam("goal") String goal,
            @RequestParam("diet_type") String dietType,
            @RequestParam("activity_level") String activityLevel,
            Model model) {

        Map<String, String> inputData = new HashMap<>();
        inputData.put("height", height);
        inputData.put("weight", weight);
        inputData.put("age", age);
        inputData.put("goal", goal);
        inputData.put("diet_type", dietType);
        inputData.put("activity_level", activityLevel);

        String dietRecommendation = caloriesService.getDietRecommendation(inputData);
        model.addAttribute("dietRecommendation", dietRecommendation);

        return "dietResult";
    }

    @GetMapping("/external")
    public RedirectView redirectToExternal() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:5000/");
        return redirectView;
    }

}
