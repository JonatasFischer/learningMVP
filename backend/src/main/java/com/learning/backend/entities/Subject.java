package com.learning.backend.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "subjects")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    protected String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "parent_subject_id")
    protected Subject parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    protected Set<Subject> childreen;


    @OneToOne(mappedBy = "subject", cascade = CascadeType.ALL)
    protected SubjectContent content;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    protected Set<Question> questions;

}
