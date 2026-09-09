package com.example.trainingmanagementsystem.service;

import com.example.trainingmanagementsystem.entity.EligibilityRule;
import com.example.trainingmanagementsystem.entity.EligibilityRuleType;
import com.example.trainingmanagementsystem.entity.Officer;
import com.example.trainingmanagementsystem.entity.TrainingProgram;
import com.example.trainingmanagementsystem.repository.EligibilityRuleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class EligibilityService {

    private final EligibilityRuleRepository eligibilityRuleRepository;

    public EligibilityService(
            EligibilityRuleRepository eligibilityRuleRepository) {

        this.eligibilityRuleRepository = eligibilityRuleRepository;
    }

    public String checkEligibility(
            Officer officer,
            TrainingProgram trainingProgram) {

        List<EligibilityRule> rules =
                eligibilityRuleRepository
                        .findByTrainingProgramId(trainingProgram.getId());

        for (EligibilityRule rule : rules) {

            switch (rule.getRuleType()) {

                case DEPARTMENT:

                    if (officer.getDepartment() == null ||
                            !officer.getDepartment()
                                    .getName()
                                    .equalsIgnoreCase(rule.getRuleValue())) {

                        return "Officer is not eligible. Required department: "
                                + rule.getRuleValue();
                    }

                    break;

                case GRADE:

                    if (officer.getGrade() == null ||
                            !officer.getGrade()
                                    .equalsIgnoreCase(rule.getRuleValue())) {

                        return "Officer is not eligible. Required grade: "
                                + rule.getRuleValue();
                    }

                    break;

                case DESIGNATION:

                    if (officer.getDesignation() == null ||
                            !officer.getDesignation()
                                    .equalsIgnoreCase(rule.getRuleValue())) {

                        return "Officer is not eligible. Required designation: "
                                + rule.getRuleValue();
                    }

                    break;

                case MIN_SERVICE_YEARS:

                    if (officer.getJoiningDate() == null) {
                        return "Officer is not eligible. Joining date is required.";
                    }

                    int serviceYears =
                            Period.between(
                                    officer.getJoiningDate(),
                                    LocalDate.now()
                            ).getYears();

                    int requiredYears =
                            Integer.parseInt(rule.getRuleValue());

                    if (serviceYears < requiredYears) {

                        return "Officer is not eligible. Minimum "
                                + requiredYears
                                + " years of service required.";
                    }

                    break;
            }
        }

        return "ELIGIBLE";
    }
}