package com.example.trainingmanagementsystem.repository;

import com.example.trainingmanagementsystem.entity.Officer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OfficerRepository
        extends JpaRepository<Officer, Long> {

    Optional<Officer> findByOfficerNumber(String officerNumber);
}
