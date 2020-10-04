package com.learning.backend.controllers;

import com.learning.backend.entities.StudyPlan;
import com.learning.backend.entities.Subject;
import com.learning.backend.repositories.StudyPlanRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController()
@RequestMapping("/studyplans")
public class StudyPlanController {

    private final StudyPlanRepository repository;

    public StudyPlanController(StudyPlanRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public List<StudyPlan> list() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public StudyPlan get(@PathVariable(value="id")Long id) {
        return  repository.getOne(id);
    }

    @PostMapping("/")
    public ResponseEntity<Object> createResource(@RequestBody StudyPlan plan) {
        StudyPlan savedPlan = repository.save(plan);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedPlan.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateResource(@PathVariable(value="id")Long id, @RequestBody StudyPlan plan) {
        StudyPlan record = repository.getOne(id);
        record.setDescription(plan.getDescription());
        record.setTitle(plan.getTitle());
        StudyPlan savedPlan = repository.save(record);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedPlan.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


}
