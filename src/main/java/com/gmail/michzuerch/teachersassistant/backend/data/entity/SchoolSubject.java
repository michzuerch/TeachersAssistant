package com.gmail.michzuerch.teachersassistant.backend.data.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/* Schulfach in german */
@Entity(name = "SchoolSubject")
public class SchoolSubject extends AbstractEntity {
    @NotNull
    private String description;

    @ManyToOne
    private School school;

    @OneToMany(mappedBy = "schoolSubject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lession> lessions = new ArrayList<>();

    @OneToMany(mappedBy = "schoolSubject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SchoolGrade> schoolGrades = new ArrayList<>();


}
