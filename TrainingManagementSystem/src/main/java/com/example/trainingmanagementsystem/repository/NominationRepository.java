package com.example.trainingmanagementsystem.repository;

import com.example.trainingmanagementsystem.entity.Nomination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NominationRepository
        extends JpaRepository<Nomination, Long> {

    boolean existsByOfficerIdAndTrainingProgramId(
            Long officerId,
            Long trainingProgramId
    );

    List<Nomination> findByTrainingProgramId(Long trainingProgramId);

    long countByTrainingProgramId(Long trainingProgramId);
}