package com.learning.backend.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "question_options")
public class QuestionOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    protected Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "question_id")
    @Getter
    @Setter
    protected Question question;

    @Lob
    @Column(name = "data", length = 1024, nullable = false, unique = true)
    @Getter
    @Setter
    protected String data;

    @Lob
    @Column(name = "expected_value", length = 1024, unique = true)
    @Getter
    @Setter
    protected String expectedValue;
}
