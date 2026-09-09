package com.example.trainingmanagementsystem.controller;



import com.example.trainingmanagementsystem.dto.NominationRequest;
import com.example.trainingmanagementsystem.entity.Nomination;
import com.example.trainingmanagementsystem.service.NominationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nominations")
@CrossOrigin
public class NominationController {

    private final NominationService service;

    public NominationController(NominationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> create(
            @Valid @RequestBody NominationRequest request) {

        try {

            Nomination nomination =
                    service.createNomination(request);

            return new ResponseEntity<>(
                    nomination,
                    HttpStatus.CREATED
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping("/training/{trainingProgramId}")
    public ResponseEntity<List<Nomination>> getByTraining(
            @PathVariable Long trainingProgramId) {

        return ResponseEntity.ok(
                service.getNominationsByTraining(
                        trainingProgramId
                )
        );
    }
}
