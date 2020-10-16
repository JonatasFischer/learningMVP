package com.learning.backend.controllers;

import com.learning.backend.entities.Subject;
import com.learning.backend.repositories.SubjectRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController()
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectRepository repository;

    public SubjectController(SubjectRepository repository) {
        this.repository = repository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public List<Subject> list(@RequestParam(required = false, name = "q") String query) {
        if (StringUtils.isEmpty(query))
            return repository.findAll();
        return repository.search(query);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public Subject get(@PathVariable(value = "id") Long id) {
        return repository.getOne(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<Object> createResource(@RequestBody Subject plan) {
        Subject savedPlan = repository.save(plan);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedPlan.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateResource(@PathVariable(value = "id") Long id, @RequestBody Subject plan) {
        plan.setId(id);
        repository.save(plan);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
        return ResponseEntity.ok(location);
    }

}
