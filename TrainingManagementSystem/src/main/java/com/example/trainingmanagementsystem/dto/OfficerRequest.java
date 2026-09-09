package com.example.trainingmanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class OfficerRequest {

    @NotBlank
    private String officerNumber;

    @NotBlank
    private String name;

    @NotNull
    private Long departmentId;

    private String grade;

    private String designation;

    private LocalDate joiningDate;

    public void setOfficerNumber(String officerNumber) {
        this.officerNumber = officerNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }
}