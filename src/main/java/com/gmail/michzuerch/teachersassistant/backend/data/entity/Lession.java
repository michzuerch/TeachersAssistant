package com.gmail.michzuerch.teachersassistant.backend.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data
public class Lession extends AbstractEntity {
    @NotNull
    @Size(min = 3, message = "Mindestlänge 3 Zeichen")
    private String bezeichnung;

    @NotNull
    private LocalDateTime start;

    @NotNull
    private LocalDateTime ende;

    @ManyToOne
    private SchoolSubject schoolSubject;

    @ManyToOne
    private Teacher teacher;

    @ManyToOne
    private Classroom classroom;

    @ManyToOne
    private SchoolClass schoolClass;

}