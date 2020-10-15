package com.learning.backend.entities;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "subject_content")
public class SubjectContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @OneToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    protected Subject subject;

    @Lob
    @Column(name = "data", length = 1024, nullable = false, unique = true)
    protected String data;
}
