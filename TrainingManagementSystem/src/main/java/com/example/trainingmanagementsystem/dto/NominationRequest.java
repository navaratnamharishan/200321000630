package com.example.trainingmanagementsystem.dto;


import jakarta.validation.constraints.NotNull;

public class NominationRequest {

    @NotNull(message = "Officer ID is required")
    private Long officerId;

    @NotNull(message = "Training program ID is required")
    private Long trainingProgramId;

    @NotNull(message = "Department ID is required")
    private Long departmentId;

    public Long getOfficerId() {
        return officerId;
    }

    public void setOfficerId(Long officerId) {
        this.officerId = officerId;
    }

    public Long getTrainingProgramId() {
        return trainingProgramId;
    }

    public void setTrainingProgramId(Long trainingProgramId) {
        this.trainingProgramId = trainingProgramId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}