package com.gmail.michzuerch.teachersassistant.backend.data.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/* Schulnote in german */
@Entity(name = "SchoolGrade")
public class SchoolGrade extends AbstractEntity {
    private String description;

    @ManyToOne
    private School school;

    private LocalDateTime localDateTime;

    private BigDecimal note;



}