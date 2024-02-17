package com.example.webportal.repositories;

import com.example.webportal.models.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {
    List<WeatherData> findByDate(LocalDate date);
    WeatherData findTopByOrderByDateDesc();
    WeatherData findTopByOrderByDateDescTimeDesc();

    List<WeatherData> findByDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT o FROM WeatherData o WHERE o.date BETWEEN :startDate AND :endDate")
    List<WeatherData> findDataByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}

