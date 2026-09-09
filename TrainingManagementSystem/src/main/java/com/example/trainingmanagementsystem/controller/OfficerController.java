package com.example.trainingmanagementsystem.controller;

import com.example.trainingmanagementsystem.dto.OfficerRequest;
import com.example.trainingmanagementsystem.entity.Officer;
import com.example.trainingmanagementsystem.service.OfficerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/officers")
@CrossOrigin
public class OfficerController {

    private final OfficerService service;

    public OfficerController(OfficerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Officer> create(
            @Valid @RequestBody OfficerRequest request) {

        return new ResponseEntity<>(
                service.createOfficer(request),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Officer>> getAll() {

        return ResponseEntity.ok(
                service.getAllOfficers()
        );
    }
}