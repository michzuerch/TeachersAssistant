package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository;


import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = com.gmail.michzuerch.LehrerVerwaltung.backend.entity.EditorTest.class)
public interface EditorTestDeltaspikeRepository extends EntityRepository<com.gmail.michzuerch.LehrerVerwaltung.backend.entity.EditorTest, Long> {
    List<com.gmail.michzuerch.LehrerVerwaltung.backend.entity.EditorTest> findByErster(String erster);

    List<com.gmail.michzuerch.LehrerVerwaltung.backend.entity.EditorTest> findByErsterLikeIgnoreCase(String filter);
}
