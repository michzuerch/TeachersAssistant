package com.gmail.michzuerch.teachersassistant.backend.data.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/* Schulnote in german */
@Entity(name = "SchoolGrade")
public class SchoolGrade extends AbstractEntity {
    @NotNull
    private String description;

    @ManyToOne
    private Student student;

    @ManyToOne
    private SchoolSubject schoolSubject;

    private LocalDateTime timestamp;

    private BigDecimal note;
}