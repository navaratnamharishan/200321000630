package com.example.trainingmanagementsystem.dto;


import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class TrainingProgramRequest {

    @NotBlank(message = "Training title is required")
    private String trainingTitle;

    @NotNull(message = "Training date is required")
    private LocalDate trainingDate;

    @NotBlank(message = "Venue is required")
    private String venue;

    @NotBlank(message = "Resource person is required")
    private String resourcePerson;

    @NotNull(message = "Maximum participants is required")
    @Min(value = 1, message = "Maximum participants must be at least 1")
    private Integer maximumParticipants;

    @NotBlank(message = "Target department is required")
    private String targetDepartment;

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
