package com.example.webportal.controllers;

import com.example.webportal.models.WeatherData;
import com.example.webportal.services.WeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;


@RestController
@CrossOrigin(origins = {"https://stacjapogodowa.serveo.net", "http://localhost:4200", "http://localhost:8000"})
public class WeatherDataController {

    private WeatherDataService weatherDataService;

    @Autowired
    public void setWeatherDataService(WeatherDataService weatherDataService){
        this.weatherDataService = weatherDataService;
    }

    @GetMapping("/wd/day/{date}")
    public List<WeatherData> getWeatherDataForDay(
            @PathVariable @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate date
    ) {
        return weatherDataService.getWeatherDataForDay(date);
    }

    @GetMapping("/wd/latest-time")
    public WeatherData getLatestWeatherDataTime() {
        return weatherDataService.getLatestWeatherDataTime();
    }

    @GetMapping("/wd/dates")
    public List<WeatherData> getObservationData(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

            LocalDate parsedStartDate = LocalDate.parse(startDate, formatter);
            LocalDate parsedEndDate = LocalDate.parse(endDate, formatter);

            return weatherDataService.getObservationsByDateRange(parsedStartDate, parsedEndDate);
        } catch (DateTimeParseException e) {
            // Złap błąd parsowania daty
            System.err.println("Date parsing error: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format", e);
        }
    }


}
