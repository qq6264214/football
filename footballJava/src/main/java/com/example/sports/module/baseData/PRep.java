package com.example.sports.module.baseData;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface PRep extends JpaRepository<Profit,Serializable> {

    Profit findFirstByOrderByIdDesc();
}
