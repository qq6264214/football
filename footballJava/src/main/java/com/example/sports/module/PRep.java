package com.example.sports.module;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface PRep extends JpaRepository<Profit,Serializable> {

    Profit findFirstByOrderByIdDesc();
}
