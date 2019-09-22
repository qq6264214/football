package com.example.sports.module.forecast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Date;
import java.util.List;

@Service
public class ForecastDataService {
    @Autowired
    private ForecastDataRep forecastDataRep;

    @PersistenceContext
    private EntityManager entityManager;

    public List list(Date startDate, Date endDate, java.util.Date startTime, java.util.Date endTime,Double value){
        String sql = "SELECT * FROM forecast_data WHERE ((bisaishijian>=? AND bisaishijian<= ? AND real_time is null) " +
                "OR (real_time is NOT NULL AND real_time>=? AND real_time <= ?)) " +
                "AND (shengmax>? OR pingmax>? or fumax>? OR shangmax>? OR xiamax>?) ORDER BY real_time";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1,startDate);
        query.setParameter(2,endDate);
        query.setParameter(3,startTime);
        query.setParameter(4,endTime);
        query.setParameter(5,value);
        query.setParameter(6,value);
        query.setParameter(7,value);
        query.setParameter(8,value);
        query.setParameter(9,value);

        List list = query.getResultList();

        return list;
    }


}
