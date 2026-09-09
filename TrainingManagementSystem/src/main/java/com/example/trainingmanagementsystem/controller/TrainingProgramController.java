package com.example.trainingmanagementsystem.controller;



import com.example.trainingmanagementsystem.dto.TrainingProgramRequest;
import com.example.trainingmanagementsystem.entity.TrainingProgram;
import com.example.trainingmanagementsystem.service.TrainingProgramService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/training-programs")
@CrossOrigin
public class TrainingProgramController {

    private final TrainingProgramService service;

    public TrainingProgramController(
            TrainingProgramService service) {

        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TrainingProgram> create(
            @Valid @RequestBody TrainingProgramRequest request) {

        return new ResponseEntity<>(
                service.createTrainingProgram(request),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<TrainingProgram>> getAll() {

        return ResponseEntity.ok(
                service.getAllTrainingPrograms()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingProgram> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.getTrainingProgramById(id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id) {

        service.deleteTrainingProgram(id);

        return ResponseEntity.ok(
                "Training program deleted successfully"
        );
    }
}