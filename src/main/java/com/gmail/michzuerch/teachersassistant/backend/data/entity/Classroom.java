package com.gmail.michzuerch.teachersassistant.backend.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Classroom extends AbstractEntity {
   private String bezeichnung;

    @ManyToOne
    private School school;

    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lession> lessions = new ArrayList<>();
}