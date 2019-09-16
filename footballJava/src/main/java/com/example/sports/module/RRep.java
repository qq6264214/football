package com.example.sports.module;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

@Repository
public interface RRep extends JpaRepository<R,Serializable> {

    List<R> findByTimeAfterAndTimeBefore( Date startTime, Date endTime);

}
