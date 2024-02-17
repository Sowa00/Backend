package com.example.webportal.services;

import com.example.webportal.exceptions.WeatherDataNotFoundException;
import com.example.webportal.models.WeatherData;
import com.example.webportal.repositories.WeatherDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class WeatherDataService {

    @Autowired
    private final WeatherDataRepository weatherDataRepository;

    public WeatherDataService(WeatherDataRepository weatherDataRepository) {
        this.weatherDataRepository = weatherDataRepository;
    }

    public WeatherData addWeatherDataO(WeatherData weatherData){
        return weatherDataRepository.save(weatherData);
    }

    public List<WeatherData> findAllWeatherData(){
        return weatherDataRepository.findAll();
    }

    public WeatherData findWeatherDataById(Long id){
        return weatherDataRepository.findById(id)
                .orElseThrow(() -> new WeatherDataNotFoundException("WeatherData by id "+ id +" was not found"));
    }

    public WeatherData showWeatherDataID(long id) {
        return weatherDataRepository.getById(id);
    }

    public List<WeatherData> getWeatherDataForDay(LocalDate date) {
        return weatherDataRepository.findByDate(date);
    }

    public List<WeatherData> getWeatherDataForDateRange(LocalDate startDate, LocalDate endDate) {
        return weatherDataRepository.findByDateBetween(startDate, endDate);
    }


    public List<WeatherData> getLatestWeatherData() {
        LocalDate date = weatherDataRepository.findTopByOrderByDateDesc().getDate();
        return getWeatherDataForDay(date);
    }

    public WeatherData getLatestWeatherDataTime() {
        return weatherDataRepository.findTopByOrderByDateDescTimeDesc();
    }

    public List<WeatherData> getObservationsByDateRange(LocalDate startDate, LocalDate endDate) {
        return weatherDataRepository.findDataByDateRange(startDate, endDate);
    }


}
