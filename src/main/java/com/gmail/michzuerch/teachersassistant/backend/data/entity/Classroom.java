package com.gmail.michzuerch.teachersassistant.backend.data.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Classroom")
public class Classroom extends AbstractEntity {
    @NotNull
    private String description;

    @ManyToOne
    private School school;

    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lession> lessions = new ArrayList<>();

    public Classroom() {
    }

    private Classroom(Builder builder) {
        description = builder.description;
        school = builder.school;
        lessions = builder.lessions;
    }


    public static final class Builder {
        private @NotNull String description;
        private School school;
        private List<Lession> lessions;

        public Builder() {
        }

        public Builder description(@NotNull String val) {
            description = val;
            return this;
        }

        public Builder school(School val) {
            school = val;
            return this;
        }

        public Builder lessions(List<Lession> val) {
            lessions = val;
            return this;
        }

        public Classroom build() {
            return new Classroom(this);
        }
    }
}