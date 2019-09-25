package com.example.sports.module.forecast;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface ForecastDataRep extends JpaRepository<ForecastData, Serializable> {

    public List<ForecastData> findByStartAnalysis(Integer startAnalysis);

}
