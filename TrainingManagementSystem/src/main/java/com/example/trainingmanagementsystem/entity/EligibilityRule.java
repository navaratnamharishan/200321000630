package com.example.trainingmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "eligibility_rules")
public class EligibilityRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "training_program_id", nullable = false)
    private TrainingProgram trainingProgram;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EligibilityRuleType ruleType;

    @Column(nullable = false)
    private String ruleValue;

    public EligibilityRule() {
    }

    public void setRuleValue(String ruleValue) {
        this.ruleValue = ruleValue;
    }
}