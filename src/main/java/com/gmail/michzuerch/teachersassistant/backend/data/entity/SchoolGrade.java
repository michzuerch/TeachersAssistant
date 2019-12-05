package com.gmail.michzuerch.teachersassistant.backend.data.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class SchoolGrade extends AbstractEntity {
    private String bezeichnung;

    @ManyToOne
    private School school;

    private LocalDateTime localDateTime;

    private float note;
}