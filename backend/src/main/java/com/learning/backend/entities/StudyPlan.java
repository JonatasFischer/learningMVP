package com.learning.backend.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="Subject")
public class StudyPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="studyplan_id")
    private long id;

    @Column(name="title", length=50, nullable=false, unique=true)
    String title;

    @Column(name="description", length=50, nullable=false, unique=true)
    String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "studyplan_subject",
            joinColumns = @JoinColumn(name = "studyplan_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))

    Set<Subject> subjects;

}
