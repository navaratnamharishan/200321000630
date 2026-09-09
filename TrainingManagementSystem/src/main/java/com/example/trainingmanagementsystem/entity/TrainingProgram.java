package com.example.trainingmanagementsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "training_programs")
public class TrainingProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NotBlank(message = "Training title is required")
    @Column(nullable = false)
    private String trainingTitle;

    @Setter
    @NotNull(message = "Training date is required")
    @Column(nullable = false)
    private LocalDate trainingDate;

    @Setter
    @NotBlank(message = "Venue is required")
    @Column(nullable = false)
    private String venue;

    @Setter
    @NotBlank(message = "Resource person is required")
    @Column(nullable = false)
    private String resourcePerson;

    @Setter
    @NotNull(message = "Maximum participants is required")
    @Min(value = 1, message = "Maximum participants must be at least 1")
    @Column(nullable = false)
    private Integer maximumParticipants;

    @Setter
    @NotBlank(message = "Target department is required")
    @Column(nullable = false)
    private String targetDepartment;

    public TrainingProgram() {
    }

}