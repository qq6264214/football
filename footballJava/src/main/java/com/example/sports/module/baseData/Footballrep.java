package com.example.sports.module.baseData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Repository
public interface Footballrep extends JpaRepository<FootballAddData,Serializable> {

    FootballAddData findFirstByBisaishijianAfterAndBisaishijianBeforeOrderByBisaishijianDesc( Date start,Date end );

    List<FootballAddData> findByBisaishijianAndFc1IsNullOrderById(Date date);
}
