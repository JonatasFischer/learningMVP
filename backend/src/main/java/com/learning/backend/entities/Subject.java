package com.learning.backend.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "subjects")

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    protected Long id;

    @Getter
    @Setter
    protected String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "parent_subject_id")
    @Getter
    @Setter
    protected Subject parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    @Getter
    @Setter
    protected Set<Subject> childreen;


    @OneToOne(mappedBy = "subject", cascade = CascadeType.ALL)
    @Getter
    @Setter
    protected SubjectContent content;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    @Getter
    @Setter
    protected Set<Question> questions;

}
