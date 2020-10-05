package com.learning.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Subject_requirement")
public class SubjectRequirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_requirement_id")
    @Getter
    @Setter
    protected Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "studyplan_id", nullable = false)
    @JsonIgnore
    @Getter
    @Setter
    protected StudyPlan plan;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    @Getter
    @Setter
    protected Subject subject;

    @Column(nullable = false)
    @Getter
    @Setter
    protected Long sequence;

    @Column(nullable = false)
    @Getter
    @Setter
    protected String minimumDomain;
}
