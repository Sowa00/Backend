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
        // Given
        WeatherData weatherData = new WeatherData();
        when(weatherDataRepository.save(weatherData)).thenReturn(weatherData);

        // When
        WeatherData result = weatherDataService.addWeatherDataO(weatherData);

        // Then
        assertEquals(weatherData, result);
        verify(weatherDataRepository).save(weatherData);
    }

    @Test
    void testFindAllWeatherData() {
        // Given
        List<WeatherData> testData = Arrays.asList(new WeatherData(), new WeatherData());
        when(weatherDataRepository.findAll()).thenReturn(testData);

        // When
        List<WeatherData> result = weatherDataService.findAllWeatherData();

        // Then
        assertEquals(testData, result);
        verify(weatherDataRepository).findAll();
    }

    @Test
    void testFindWeatherDataById() {
        // Given
        Long id = 1L;
        WeatherData testData = new WeatherData();
        when(weatherDataRepository.findById(id)).thenReturn(Optional.of(testData));

        // When
        WeatherData result = weatherDataService.findWeatherDataById(id);

        // Then
        assertEquals(testData, result);
        verify(weatherDataRepository).findById(id);
    }

    @Test
    void testFindWeatherDataByIdNotFound() {
        // Given
        Long id = 1L;
        when(weatherDataRepository.findById(id)).thenReturn(Optional.empty());

        // When, Then
        assertThrows(WeatherDataNotFoundException.class, () -> weatherDataService.findWeatherDataById(id));
        verify(weatherDataRepository).findById(id);
    }

    @Test
    void testShowWeatherDataID() {
        // Given
        long id = 1L;
        WeatherData testData = new WeatherData();
        when(weatherDataRepository.getById(id)).thenReturn(testData);

        // When
        WeatherData result = weatherDataService.showWeatherDataID(id);

        // Then
        assertEquals(testData, result);
        verify(weatherDataRepository).getById(id);
    }

    @Test
    void testGetWeatherDataForDay() {
        // Given
        LocalDate date = LocalDate.now();
        List<WeatherData> testData = Arrays.asList(new WeatherData(), new WeatherData());
        when(weatherDataRepository.findByDate(date)).thenReturn(testData);

        // When
        List<WeatherData> result = weatherDataService.getWeatherDataForDay(date);

        // Then
        assertEquals(testData, result);
        verify(weatherDataRepository).findByDate(date);
    }

    @Test
    void testGetWeatherDataForDateRange() {
        // Given
        LocalDate startDate = LocalDate.now().minusDays(5);
        LocalDate endDate = LocalDate.now();
        List<WeatherData> testData = Arrays.asList(new WeatherData(), new WeatherData());
        when(weatherDataRepository.findByDateBetween(startDate, endDate)).thenReturn(testData);

        // When
        List<WeatherData> result = weatherDataService.getWeatherDataForDateRange(startDate, endDate);

        // Then
        assertEquals(testData, result);
        verify(weatherDataRepository).findByDateBetween(startDate, endDate);
    }

    @Test
    void testGetLatestWeatherDataTime() {
        // Given
        WeatherData latestWeatherDataTime = new WeatherData();
        when(weatherDataRepository.findTopByOrderByDateDescTimeDesc()).thenReturn(latestWeatherDataTime);

        // When
        WeatherData result = weatherDataService.getLatestWeatherDataTime();

        // Then
        assertEquals(latestWeatherDataTime, result);
        verify(weatherDataRepository).findTopByOrderByDateDescTimeDesc();
    }

    @Test
    void testGetObservationsByDateRange() {
        // Given
        LocalDate startDate = LocalDate.now().minusDays(5);
        LocalDate endDate = LocalDate.now();
        List<WeatherData> testData = Arrays.asList(new WeatherData(), new WeatherData());
        when(weatherDataRepository.findDataByDateRange(startDate, endDate)).thenReturn(testData);

        // When
        List<WeatherData> result = weatherDataService.getObservationsByDateRange(startDate, endDate);

        // Then
        assertEquals(testData, result);
        verify(weatherDataRepository).findDataByDateRange(startDate, endDate);
    }

}
