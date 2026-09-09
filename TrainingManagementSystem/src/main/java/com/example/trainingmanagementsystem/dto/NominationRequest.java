package com.example.trainingmanagementsystem.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class NominationRequest {

    @NotNull(message = "Officer ID is required")
    private Long officerId;

    @NotNull(message = "Training program ID is required")
    private Long trainingProgramId;

    @NotNull(message = "Department ID is required")
    private Long departmentId;

    public void setOfficerId(Long officerId) {
        this.officerId = officerId;
    }

    public void setTrainingProgramId(Long trainingProgramId) {
        this.trainingProgramId = trainingProgramId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}