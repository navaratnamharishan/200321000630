package com.example.trainingmanagementsystem.service;



import com.example.trainingmanagementsystem.dto.TrainingProgramRequest;
import com.example.trainingmanagementsystem.entity.TrainingProgram;
import com.example.trainingmanagementsystem.repository.TrainingProgramRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingProgramService {

    private final TrainingProgramRepository repository;

    public TrainingProgramService(TrainingProgramRepository repository) {
        this.repository = repository;
    }

    public TrainingProgram createTrainingProgram(
            TrainingProgramRequest request) {

        TrainingProgram trainingProgram = new TrainingProgram();

        trainingProgram.setTrainingTitle(request.getTrainingTitle());
        trainingProgram.setTrainingDate(request.getTrainingDate());
        trainingProgram.setVenue(request.getVenue());
        trainingProgram.setResourcePerson(request.getResourcePerson());
        trainingProgram.setMaximumParticipants(
                request.getMaximumParticipants()
        );
        trainingProgram.setTargetDepartment(
                request.getTargetDepartment()
        );

        return repository.save(trainingProgram);
    }

    public List<TrainingProgram> getAllTrainingPrograms() {
        return repository.findAll();
    }

    public TrainingProgram getTrainingProgramById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Training program not found"
                        ));
    }

    public void deleteTrainingProgram(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException(
                    "Training program not found"
            );
        }

        repository.deleteById(id);
    }
}