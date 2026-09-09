package com.example.trainingmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
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

    @Setter
    @ManyToOne
    @JoinColumn(name = "officer_id", nullable = false)
    private Officer officer;

    @Setter
    @ManyToOne
    @JoinColumn(name = "training_program_id", nullable = false)
    private TrainingProgram trainingProgram;

    @Setter
    @ManyToOne
    @JoinColumn(name = "nominating_department_id", nullable = false)
    private Department nominatingDepartment;

    @Setter
    @Column(nullable = false)
    private LocalDateTime nominatedAt;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NominationStatus status;

    public Nomination() {
    }

}