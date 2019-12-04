package com.gmail.michzuerch.teachersassistant.backend.data.entity;

@Entity
@Data
public class Classroom extends AbstractEntity {
   private String bezeichnung;

    @ManyToOne
    private School school;

    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lession> lessions = new ArrayList<>();
}