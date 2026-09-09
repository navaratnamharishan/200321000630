package com.example.trainingmanagementsystem.service;

import com.example.trainingmanagementsystem.dto.NominationRequest;
import com.example.trainingmanagementsystem.entity.*;
import com.example.trainingmanagementsystem.repository.DepartmentRepository;
import com.example.trainingmanagementsystem.repository.NominationRepository;
import com.example.trainingmanagementsystem.repository.OfficerRepository;
import com.example.trainingmanagementsystem.repository.TrainingProgramRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                        new RuntimeException("Officer not found"));

        // 2. Check training program
        TrainingProgram trainingProgram =
                trainingProgramRepository
                        .findById(request.getTrainingProgramId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Training program not found"));

        // 3. Check department
        Department department =
                departmentRepository
                        .findById(request.getDepartmentId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Department not found"));

        // 4. Check duplicate nomination
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

        // 5. Count only CONFIRMED participants
        long confirmedCount =
                nominationRepository
                        .countByTrainingProgramIdAndStatus(
                                request.getTrainingProgramId(),
                                NominationStatus.CONFIRMED
                        );

        // 6. Create nomination
        Nomination nomination = new Nomination();

        nomination.setOfficer(officer);
        nomination.setTrainingProgram(trainingProgram);
        nomination.setNominatingDepartment(department);
        nomination.setNominatedAt(LocalDateTime.now());

        // 7. Confirm if seats are available
        //    Otherwise put on waiting list
        if (confirmedCount < trainingProgram.getMaximumParticipants()) {

            nomination.setStatus(NominationStatus.CONFIRMED);

        } else {

            nomination.setStatus(NominationStatus.WAITING_LIST);
        }

        return nominationRepository.save(nomination);
    }


    @Transactional
    public String cancelNomination(Long nominationId) {

        Nomination nomination =
                nominationRepository.findById(nominationId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Nomination not found"));

        if (nomination.getStatus() == NominationStatus.CANCELLED) {
            return "Nomination is already cancelled.";
        }

        // Remember whether this was a confirmed participant
        boolean wasConfirmed =
                nomination.getStatus() == NominationStatus.CONFIRMED;

        // Cancel nomination
        nomination.setStatus(NominationStatus.CANCELLED);
        nominationRepository.save(nomination);

        // If confirmed participant cancelled,
        // promote first waiting-list person
        if (wasConfirmed) {

            List<Nomination> waitingList =
                    nominationRepository
                            .findByTrainingProgramIdAndStatusOrderByNominatedAtAsc(
                                    nomination.getTrainingProgram().getId(),
                                    NominationStatus.WAITING_LIST
                            );

            if (!waitingList.isEmpty()) {

                Nomination nextNomination = waitingList.get(0);

                nextNomination.setStatus(
                        NominationStatus.CONFIRMED
                );

                nominationRepository.save(nextNomination);

                return "Nomination cancelled. Officer "
                        + nextNomination.getOfficer().getName()
                        + " has been promoted from the waiting list.";
            }
        }

        return "Nomination cancelled. No one is waiting.";
    }


    public List<Nomination> getNominationsByTraining(
            Long trainingProgramId) {

        return nominationRepository
                .findByTrainingProgramId(trainingProgramId);
    }
}