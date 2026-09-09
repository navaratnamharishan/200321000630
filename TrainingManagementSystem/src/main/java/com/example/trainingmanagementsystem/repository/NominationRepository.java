package com.example.trainingmanagementsystem.repository;

import com.example.trainingmanagementsystem.entity.Nomination;
import com.example.trainingmanagementsystem.entity.NominationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface NominationRepository
        extends JpaRepository<Nomination, Long> {

    boolean existsByOfficerIdAndTrainingProgramId(
            Long officerId,
            Long trainingProgramId
    );

    boolean existsByOfficerIdAndTrainingProgramIdAndNominatedAtAfter(
            Long officerId,
            Long trainingProgramId,
            LocalDateTime date
    );

    long countByTrainingProgramIdAndStatus(
            Long trainingProgramId,
            NominationStatus status
    );

    List<Nomination> findByTrainingProgramId(
            Long trainingProgramId
    );

    List<Nomination>
    findByTrainingProgramIdAndStatusOrderByNominatedAtAsc(
            Long trainingProgramId,
            NominationStatus status
    );
}