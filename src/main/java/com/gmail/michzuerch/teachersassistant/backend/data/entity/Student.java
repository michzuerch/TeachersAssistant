package com.gmail.michzuerch.teachersassistant.backend.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data
public class Student extends AbstractEntity {

    private String vorname;

    private String nachname;

    @ManyToOne
    private SchoolClass schoolClass;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SchoolSubject> schoolSubjects = new ArrayList<>();

}