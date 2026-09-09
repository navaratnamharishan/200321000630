package com.example.trainingmanagementsystem.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "nominations",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_officer_training",
                        columnNames = {"officer_id", "training_program_id"}
                )
        }
)
public class Nomination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "officer_id", nullable = false)
    private Officer officer;

    @ManyToOne
    @JoinColumn(name = "training_program_id", nullable = false)
    private TrainingProgram trainingProgram;

    @ManyToOne
    @JoinColumn(name = "nominating_department_id", nullable = false)
    private Department nominatingDepartment;

    @Column(nullable = false)
    private LocalDateTime nominatedAt;

    public Nomination() {
    }

    public Long getId() {
        return id;
    }

    public Officer getOfficer() {
        return officer;
    }

    public void setOfficer(Officer officer) {
        this.officer = officer;
    }

    public TrainingProgram getTrainingProgram() {
        return trainingProgram;
    }

    public void setTrainingProgram(TrainingProgram trainingProgram) {
        this.trainingProgram = trainingProgram;
    }

    public Department getNominatingDepartment() {
        return nominatingDepartment;
    }

    public void setNominatingDepartment(Department nominatingDepartment) {
        this.nominatingDepartment = nominatingDepartment;
    }

    public LocalDateTime getNominatedAt() {
        return nominatedAt;
    }

    public void setNominatedAt(LocalDateTime nominatedAt) {
        this.nominatedAt = nominatedAt;
    }
}