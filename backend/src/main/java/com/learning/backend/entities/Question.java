package com.learning.backend.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    protected Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "subject_id")
    @Getter
    @Setter
    protected Subject subject;

    @Getter
    @Setter
    protected String content;

    @Getter
    @Setter
    protected String type;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Getter
    @Setter
    protected Set<QuestionOption> options;
}
