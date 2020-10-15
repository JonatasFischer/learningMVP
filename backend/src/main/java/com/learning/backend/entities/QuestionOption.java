package com.learning.backend.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "question_options")
public class QuestionOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "question_id")
    protected Question question;

    @Lob
    @Column(name = "data", length = 1024, nullable = false, unique = true)
    protected String data;

    @Lob
    @Column(name = "expected_value", length = 1024, unique = true)
    protected String expectedValue;
}
