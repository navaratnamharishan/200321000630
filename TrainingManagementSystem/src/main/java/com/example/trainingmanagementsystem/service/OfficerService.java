package com.example.trainingmanagementsystem.service;


import com.example.trainingmanagementsystem.dto.OfficerRequest;
import com.example.trainingmanagementsystem.entity.Department;
import com.example.trainingmanagementsystem.entity.Officer;
import com.example.trainingmanagementsystem.repository.DepartmentRepository;
import com.example.trainingmanagementsystem.repository.OfficerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficerService {

    private final OfficerRepository officerRepository;
    private final DepartmentRepository departmentRepository;

    public OfficerService(
            OfficerRepository officerRepository,
            DepartmentRepository departmentRepository) {

        this.officerRepository = officerRepository;
        this.departmentRepository = departmentRepository;
    }

    public Officer createOfficer(OfficerRequest request) {

        if (officerRepository
                .findByOfficerNumber(request.getOfficerNumber())
                .isPresent()) {

            throw new RuntimeException(
                    "Officer number already exists"
            );
        }

        Department department = departmentRepository
                .findById(request.getDepartmentId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Department not found"
                        ));

        Officer officer = new Officer();

        officer.setOfficerNumber(request.getOfficerNumber());
        officer.setName(request.getName());
        officer.setDepartment(department);

        return officerRepository.save(officer);
    }

    public List<Officer> getAllOfficers() {
        return officerRepository.findAll();
    }
}