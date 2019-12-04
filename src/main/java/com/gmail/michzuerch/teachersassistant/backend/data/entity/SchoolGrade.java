package com.gmail.michzuerch.teachersassistant.backend.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Data
public class SchoolGrade extends AbstractEntity {
    private String bezeichnung;

    @ManyToOne
    private SchoolSubject schoolSubject;

    private LocalDateTime localDateTime;

    private float note;
}