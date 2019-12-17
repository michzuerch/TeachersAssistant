package com.gmail.michzuerch.teachersassistant.backend.data.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity(name = "Lession")
public class Lession extends AbstractEntity {
    @NotNull
    @Size(min = 3, message = "Mindestlänge 3 Zeichen")
    private String description;

    @NotNull
    private LocalDateTime start;

    @NotNull
    private LocalDateTime ende;

    // Schulfach in german
    @ManyToOne
    private SchoolSubject schoolSubject;

    @ManyToOne
    private Classroom classroom;
}