package com.example.trainingmanagementsystem.repository;


import com.example.trainingmanagementsystem.entity.TrainingProgram;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingProgramRepository
        extends JpaRepository<TrainingProgram, Long> {
}
