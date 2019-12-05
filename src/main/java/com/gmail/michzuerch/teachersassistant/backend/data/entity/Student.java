package com.gmail.michzuerch.teachersassistant.backend.data.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Entity
@Data
public class Student extends AbstractEntity {

    private String vorname;

    private String nachname;

    @ManyToOne
    private SchoolClass schoolClass;

    @ManyToOne
    private School school;
}