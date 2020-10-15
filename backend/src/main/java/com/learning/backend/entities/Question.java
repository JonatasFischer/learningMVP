package com.learning.backend.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "subject_id")
    protected Subject subject;

    protected String content;

    protected String type;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    protected Set<QuestionOption> options;
}
