package com.learning.backend.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "subject")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    protected Long id;

    protected String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_subject_id")
    protected Subject parent;

    @OneToMany(mappedBy = "parent")
    protected Set<Subject> childreen;

}
