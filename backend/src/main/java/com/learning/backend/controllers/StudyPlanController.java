package com.learning.backend.controllers;

import com.learning.backend.entities.StudyPlan;
import com.learning.backend.repositories.StudyPlanRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController()
@RequestMapping("/studyplans")
@CrossOrigin(maxAge = 3600)
public class StudyPlanController {

    private StudyPlanRepository repository;

    public StudyPlanController(StudyPlanRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public List<StudyPlan> list() {
        return repository.findAll();
    }

    @PutMapping("/")
    public ResponseEntity<Object> createNew(@RequestBody StudyPlan plan) {
        StudyPlan savedPlan = repository.save(plan);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedPlan.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


}
