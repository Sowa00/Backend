package com.example.webportal.services;

import com.example.webportal.exceptions.WeatherDataNotFoundException;
import com.example.webportal.models.WeatherData;
import com.example.webportal.repositories.WeatherDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WeatherDataServiceTest {

    @Mock
    private WeatherDataRepository weatherDataRepository;

    @InjectMocks
    private WeatherDataService weatherDataService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddWeatherData() {
        WeatherData weatherData = new WeatherData();
        when(weatherDataRepository.save(weatherData)).thenReturn(weatherData);

        WeatherData result = weatherDataService.addWeatherDataO(weatherData);

        assertEquals(weatherData, result);
        verify(weatherDataRepository).save(weatherData);
    }

    @Test
    void testFindAllWeatherData() {
        List<WeatherData> testData = Arrays.asList(new WeatherData(), new WeatherData());
        when(weatherDataRepository.findAll()).thenReturn(testData);

        List<WeatherData> result = weatherDataService.findAllWeatherData();

        assertEquals(testData, result);
        verify(weatherDataRepository).findAll();
    }

    @Test
    void testFindWeatherDataById() {
        Long id = 1L;
        WeatherData testData = new WeatherData();
        when(weatherDataRepository.findById(id)).thenReturn(Optional.of(testData));

        WeatherData result = weatherDataService.findWeatherDataById(id);

        assertEquals(testData, result);
        verify(weatherDataRepository).findById(id);
    }

    @Test
    void testFindWeatherDataByIdNotFound() {
        Long id = 1L;
        when(weatherDataRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(WeatherDataNotFoundException.class, () -> weatherDataService.findWeatherDataById(id));
        verify(weatherDataRepository).findById(id);
    }

    @Test
    void testShowWeatherDataID() {
        long id = 1L;
        WeatherData testData = new WeatherData();
        when(weatherDataRepository.getById(id)).thenReturn(testData);

        WeatherData result = weatherDataService.showWeatherDataID(id);

        assertEquals(testData, result);
        verify(weatherDataRepository).getById(id);
    }

    @Test
    void testGetWeatherDataForDay() {
        LocalDate date = LocalDate.now();
        List<WeatherData> testData = Arrays.asList(new WeatherData(), new WeatherData());
        when(weatherDataRepository.findByDate(date)).thenReturn(testData);

        List<WeatherData> result = weatherDataService.getWeatherDataForDay(date);

        assertEquals(testData, result);
        verify(weatherDataRepository).findByDate(date);
    }

    @Test
    void testGetWeatherDataForDateRange() {
        LocalDate startDate = LocalDate.now().minusDays(5);
        LocalDate endDate = LocalDate.now();
        List<WeatherData> testData = Arrays.asList(new WeatherData(), new WeatherData());
        when(weatherDataRepository.findByDateBetween(startDate, endDate)).thenReturn(testData);

        List<WeatherData> result = weatherDataService.getWeatherDataForDateRange(startDate, endDate);

        assertEquals(testData, result);
        verify(weatherDataRepository).findByDateBetween(startDate, endDate);
    }

    @Test
    void testGetLatestWeatherDataTime() {
        WeatherData latestWeatherDataTime = new WeatherData();
        when(weatherDataRepository.findTopByOrderByDateDescTimeDesc()).thenReturn(latestWeatherDataTime);

        WeatherData result = weatherDataService.getLatestWeatherDataTime();

        assertEquals(latestWeatherDataTime, result);
        verify(weatherDataRepository).findTopByOrderByDateDescTimeDesc();
    }

    @Test
    void testGetObservationsByDateRange() {
        LocalDate startDate = LocalDate.now().minusDays(5);
        LocalDate endDate = LocalDate.now();
        List<WeatherData> testData = Arrays.asList(new WeatherData(), new WeatherData());
        when(weatherDataRepository.findDataByDateRange(startDate, endDate)).thenReturn(testData);

        List<WeatherData> result = weatherDataService.getObservationsByDateRange(startDate, endDate);

        assertEquals(testData, result);
        verify(weatherDataRepository).findDataByDateRange(startDate, endDate);
    }

}
