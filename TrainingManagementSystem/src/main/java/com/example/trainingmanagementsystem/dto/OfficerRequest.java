package com.example.trainingmanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class OfficerRequest {

    @NotBlank(message = "Officer number is required")
    private String officerNumber;

    @NotBlank(message = "Officer name is required")
    private String name;

    @NotNull(message = "Department ID is required")
    private Long departmentId;

    public void setOfficerNumber(String officerNumber) {
        this.officerNumber = officerNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}