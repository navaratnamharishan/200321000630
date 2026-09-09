package com.example.trainingmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Entity
@Table(
        name = "officers",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "officerNumber")
        }
)
public class Officer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String officerNumber;

    @Setter
    @Column(nullable = false)
    private String name;

    @Setter
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Setter
    private String grade;

    @Setter
    private String designation;

    @Setter
    private LocalDate joiningDate;

    public Officer() {
    }

}