package com.example.webportal.controllers;

import com.example.webportal.controllers.WeatherDataController;
import com.example.webportal.models.WeatherData;
import com.example.webportal.services.WeatherDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WeatherDataControllerTest {

    @Mock
    private WeatherDataService weatherDataService;

    @InjectMocks
    private WeatherDataController weatherDataController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetWeatherDataForDay() {
        // Given
        LocalDate date = LocalDate.now();
        List<WeatherData> testData = Arrays.asList(new WeatherData(), new WeatherData());
        when(weatherDataService.getWeatherDataForDay(date)).thenReturn(testData);

        // When
        List<WeatherData> result = weatherDataController.getWeatherDataForDay(date);

        // Then
        assertEquals(testData, result);
        verify(weatherDataService).getWeatherDataForDay(date);
    }

    @Test
    void testGetLatestWeatherDataTime() {
        // Given
        WeatherData latestWeatherDataTime = new WeatherData();
        when(weatherDataService.getLatestWeatherDataTime()).thenReturn(latestWeatherDataTime);

        // When
        WeatherData result = weatherDataController.getLatestWeatherDataTime();

        // Then
        assertEquals(latestWeatherDataTime, result);
        verify(weatherDataService).getLatestWeatherDataTime();
    }

    @Test
    void testGetObservationData() {
        // Given
        String startDate = "01.01.2022";
        String endDate = "05.01.2022";
        List<WeatherData> testData = Arrays.asList(new WeatherData(), new WeatherData());
        when(weatherDataService.getObservationsByDateRange(any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(testData);

        // When
        List<WeatherData> result = weatherDataController.getObservationData(startDate, endDate);

        // Then
        assertEquals(testData, result);
        verify(weatherDataService).getObservationsByDateRange(any(LocalDate.class), any(LocalDate.class));
    }

    @Test
    void testGetObservationDataWithInvalidDate() {
        // Given
        String startDate = "invalid-date";
        String endDate = "2022-01-01";

        // When, Then
        assertThrows(ResponseStatusException.class,
                () -> weatherDataController.getObservationData(startDate, endDate));
    }
}
