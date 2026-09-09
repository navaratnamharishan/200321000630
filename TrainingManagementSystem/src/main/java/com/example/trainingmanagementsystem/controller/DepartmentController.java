package com.example.trainingmanagementsystem.controller;


import com.example.trainingmanagementsystem.entity.Department;
import com.example.trainingmanagementsystem.repository.DepartmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin
public class DepartmentController {

    private final DepartmentRepository repository;

    public DepartmentController(
            DepartmentRepository repository) {

        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Department> create(
            @RequestBody Department department) {

        return new ResponseEntity<>(
                repository.save(department),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAll() {

        return ResponseEntity.ok(
                repository.findAll()
        );
    }
}