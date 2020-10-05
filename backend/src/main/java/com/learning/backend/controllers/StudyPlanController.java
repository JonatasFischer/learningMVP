package com.learning.backend.controllers;

import com.learning.backend.entities.StudyPlan;
import com.learning.backend.entities.SubjectRequirement;
import com.learning.backend.repositories.StudyPlanRepository;
import com.learning.backend.repositories.SubjectRequirementRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/studyplans")
public class StudyPlanController {

    private final StudyPlanRepository repository;
    private final SubjectRequirementRepository requirementRepository;

    public StudyPlanController(StudyPlanRepository repository, SubjectRequirementRepository requirementRepository) {
        this.repository = repository;
        this.requirementRepository = requirementRepository;
    }

    @GetMapping("/")
    public List<StudyPlan> list() {
        List<StudyPlan> result = repository.findAll();

        return result;
    }

    @GetMapping("/{id}")
    public StudyPlan get(@PathVariable(value = "id") Long id) {
        return repository.getOne(id);
    }

    @PostMapping("/")
    @Transactional
    public ResponseEntity<Object> createResource(@RequestBody StudyPlan plan) {
        StudyPlan savedPlan = repository.save(plan);
        //persistRequirementsFromRemoteToDb(plan, savedPlan);
        savedPlan = repository.getOne(savedPlan.getId());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedPlan.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    protected void persistRequirementsFromRemoteToDb(StudyPlan remote, StudyPlan record) {

        List<SubjectRequirement> deletedRecord = record.getSubjects().stream()
                .filter(dbRecord -> remote.getSubjects().stream().noneMatch(remReq -> dbRecord.getId().equals(remReq.getId())))
                .collect(Collectors.toList());

        deletedRecord.forEach(requirement -> {
            requirementRepository.deleteById(requirement.getId());
            requirementRepository.flush();
            record.getSubjects().remove(requirement);
        });

        List<SubjectRequirement> updatedList = record.getSubjects().stream()
                .filter(dbRecord -> remote.getSubjects().stream().anyMatch(remReq -> dbRecord.getId().equals(remReq.getId()) && (
                        !dbRecord.getSequence().equals(remReq.getSequence())
                                || !dbRecord.getSubject().getId().equals(remReq.getSequence())
                )))
                .collect(Collectors.toList());

        updatedList.forEach(updateRecord -> {
            requirementRepository.save(updateRecord);
            remote.getSubjects().remove(updateRecord);
        });
        remote.getSubjects().forEach(s -> {
            s.setPlan(record);
            requirementRepository.save(s);
        });
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> updateResource(@PathVariable(value = "id") Long id, @RequestBody StudyPlan remotePlan) {
        StudyPlan record = repository.getOne(id);
        record.setDescription(remotePlan.getDescription());
        record.setTitle(remotePlan.getTitle());

        StudyPlan dbPlan = repository.saveAndFlush(record);
        persistRequirementsFromRemoteToDb(remotePlan, record);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dbPlan.getId()).toUri();
        return ResponseEntity.ok(location);
    }


}
