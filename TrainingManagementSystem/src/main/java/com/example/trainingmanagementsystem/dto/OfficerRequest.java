package com.example.trainingmanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OfficerRequest {

    @NotBlank(message = "Officer number is required")
    private String officerNumber;

    @NotBlank(message = "Officer name is required")
    private String name;

    @NotNull(message = "Department ID is required")
    private Long departmentId;

    public String getOfficerNumber() {
        return officerNumber;
    }

    public void setOfficerNumber(String officerNumber) {
        this.officerNumber = officerNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}