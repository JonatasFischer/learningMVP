package com.learning.backend.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "subject_content")
public class SubjectContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    protected Long id;

    @OneToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    @Getter
    @Setter
    protected Subject subject;

    @Lob
    @Column(name = "data", length = 1024, nullable = false, unique = true)
    @Getter
    @Setter
    protected String data;
}
