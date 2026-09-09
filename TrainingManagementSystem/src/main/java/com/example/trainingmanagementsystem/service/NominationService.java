package com.example.trainingmanagementsystem.service;


import com.example.trainingmanagementsystem.dto.NominationRequest;
import com.example.trainingmanagementsystem.entity.Department;
import com.example.trainingmanagementsystem.entity.Nomination;
import com.example.trainingmanagementsystem.entity.Officer;
import com.example.trainingmanagementsystem.entity.TrainingProgram;
import com.example.trainingmanagementsystem.repository.DepartmentRepository;
import com.example.trainingmanagementsystem.repository.NominationRepository;
import com.example.trainingmanagementsystem.repository.OfficerRepository;
import com.example.trainingmanagementsystem.repository.TrainingProgramRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NominationService {

    private final NominationRepository nominationRepository;
    private final OfficerRepository officerRepository;
    private final TrainingProgramRepository trainingProgramRepository;
    private final DepartmentRepository departmentRepository;

    public NominationService(
            NominationRepository nominationRepository,
            OfficerRepository officerRepository,
            TrainingProgramRepository trainingProgramRepository,
            DepartmentRepository departmentRepository) {

        this.nominationRepository = nominationRepository;
        this.officerRepository = officerRepository;
        this.trainingProgramRepository = trainingProgramRepository;
        this.departmentRepository = departmentRepository;
    }

    public Nomination createNomination(NominationRequest request) {

        // 1. Check officer
        Officer officer = officerRepository
                .findById(request.getOfficerId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Officer not found"
                        ));

        // 2. Check training program
        TrainingProgram trainingProgram =
                trainingProgramRepository
                        .findById(request.getTrainingProgramId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Training program not found"
                                ));

        // 3. Check department
        Department department =
                departmentRepository
                        .findById(request.getDepartmentId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Department not found"
                                ));

        // 4. DUPLICATE CHECK
        boolean alreadyNominated =
                nominationRepository
                        .existsByOfficerIdAndTrainingProgramId(
                                request.getOfficerId(),
                                request.getTrainingProgramId()
                        );

        if (alreadyNominated) {

            throw new RuntimeException(
                    "DUPLICATE NOMINATION: Officer "
                            + officer.getName()
                            + " has already been nominated for this training program."
            );
        }

        // 5. Check maximum participants
        long currentParticipants =
                nominationRepository.countByTrainingProgramId(
                        request.getTrainingProgramId()
                );

        if (currentParticipants >=
                trainingProgram.getMaximumParticipants()) {

            throw new RuntimeException(
                    "Training program has reached the maximum number of participants."
            );
        }

        // 6. Create nomination
        Nomination nomination = new Nomination();

        nomination.setOfficer(officer);
        nomination.setTrainingProgram(trainingProgram);
        nomination.setNominatingDepartment(department);
        nomination.setNominatedAt(LocalDateTime.now());

        return nominationRepository.save(nomination);
    }

    public List<Nomination> getNominationsByTraining(
            Long trainingProgramId) {

        return nominationRepository
                .findByTrainingProgramId(trainingProgramId);
    }
}