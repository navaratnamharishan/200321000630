package com.example.trainingmanagementsystem.controller;

import com.example.trainingmanagementsystem.dto.EligibilityRuleRequest;
import com.example.trainingmanagementsystem.entity.EligibilityRule;
import com.example.trainingmanagementsystem.entity.TrainingProgram;
import com.example.trainingmanagementsystem.repository.EligibilityRuleRepository;
import com.example.trainingmanagementsystem.repository.TrainingProgramRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eligibility-rules")
@CrossOrigin
public class EligibilityRuleController {

    private final EligibilityRuleRepository ruleRepository;
    private final TrainingProgramRepository trainingProgramRepository;

    public EligibilityRuleController(
            EligibilityRuleRepository ruleRepository,
            TrainingProgramRepository trainingProgramRepository) {

        this.ruleRepository = ruleRepository;
        this.trainingProgramRepository = trainingProgramRepository;
    }

    @PostMapping
    public ResponseEntity<?> createRule(
            @Valid @RequestBody EligibilityRuleRequest request) {

        try {

            TrainingProgram trainingProgram =
                    trainingProgramRepository
                            .findById(request.getTrainingProgramId())
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Training program not found"));

            EligibilityRule rule = new EligibilityRule();

            rule.setTrainingProgram(trainingProgram);
            rule.setRuleType(request.getRuleType());
            rule.setRuleValue(request.getRuleValue());

            return new ResponseEntity<>(
                    ruleRepository.save(rule),
                    HttpStatus.CREATED
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping("/training/{trainingProgramId}")
    public ResponseEntity<List<EligibilityRule>> getRules(
            @PathVariable Long trainingProgramId) {

        return ResponseEntity.ok(
                ruleRepository.findByTrainingProgramId(
                        trainingProgramId
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRule(
            @PathVariable Long id) {

        if (!ruleRepository.existsById(id)) {

            return ResponseEntity
                    .badRequest()
                    .body("Eligibility rule not found");
        }

        ruleRepository.deleteById(id);

        return ResponseEntity.ok(
                "Eligibility rule deleted successfully"
        );
    }
}