package com.learning.backend.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "Subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    long id;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "subjects")
    Set<StudyPlan> studyPlans;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_subject_id")
    Subject parent;

    @OneToMany(mappedBy = "parent")
    Set<Subject> childreen;

}
