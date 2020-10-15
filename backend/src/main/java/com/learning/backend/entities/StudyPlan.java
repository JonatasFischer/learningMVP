package com.learning.backend.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "studyplans")
public class StudyPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected long id;

    @Column(name = "title", length = 50, nullable = false, unique = true)
    protected String title;
    @Lob
    @Column(name = "description", length = 1024, nullable = false, unique = true)
    protected String description;

    @OneToMany(mappedBy = "plan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy("sequence ASC")
    protected Set<SubjectRequirement> subjects;
}
