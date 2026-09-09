package com.example.trainingmanagementsystem.dto;

import com.example.trainingmanagementsystem.entity.EligibilityRuleType;
import jakarta.validation.constraints.NotNull;

public class EligibilityRuleRequest {

    @NotNull
    private Long trainingProgramId;

    @NotNull
    private EligibilityRuleType ruleType;

    @NotNull
    private String ruleValue;

    public Long getTrainingProgramId() {
        return trainingProgramId;
    }

    public void setTrainingProgramId(Long trainingProgramId) {
        this.trainingProgramId = trainingProgramId;
    }

    public EligibilityRuleType getRuleType() {
        return ruleType;
    }

    public void setRuleType(EligibilityRuleType ruleType) {
        this.ruleType = ruleType;
    }

    public String getRuleValue() {
        return ruleValue;
    }

    public void setRuleValue(String ruleValue) {
        this.ruleValue = ruleValue;
    }
}
