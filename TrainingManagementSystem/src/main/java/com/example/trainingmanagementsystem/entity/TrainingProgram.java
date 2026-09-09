package com.example.trainingmanagementsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
@Table(name = "training_programs")
public class TrainingProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Training title is required")
    @Column(nullable = false)
    private String trainingTitle;

    @NotNull(message = "Training date is required")
    @Column(nullable = false)
    private LocalDate trainingDate;

    @NotBlank(message = "Venue is required")
    @Column(nullable = false)
    private String venue;

    @NotBlank(message = "Resource person is required")
    @Column(nullable = false)
    private String resourcePerson;

    @NotNull(message = "Maximum participants is required")
    @Min(value = 1, message = "Maximum participants must be at least 1")
    @Column(nullable = false)
    private Integer maximumParticipants;

    @NotBlank(message = "Target department is required")
    @Column(nullable = false)
    private String targetDepartment;

    public TrainingProgram() {
    }

    public Long getId() {
        return id;
    }

    public String getTrainingTitle() {
        return trainingTitle;
    }

    public void setTrainingTitle(String trainingTitle) {
        this.trainingTitle = trainingTitle;
    }

    public LocalDate getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(LocalDate trainingDate) {
        this.trainingDate = trainingDate;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getResourcePerson() {
        return resourcePerson;
    }

    public void setResourcePerson(String resourcePerson) {
        this.resourcePerson = resourcePerson;
    }

    public Integer getMaximumParticipants() {
        return maximumParticipants;
    }

    public void setMaximumParticipants(Integer maximumParticipants) {
        this.maximumParticipants = maximumParticipants;
    }

    public String getTargetDepartment() {
        return targetDepartment;
    }

    public void setTargetDepartment(String targetDepartment) {
        this.targetDepartment = targetDepartment;
    }
}