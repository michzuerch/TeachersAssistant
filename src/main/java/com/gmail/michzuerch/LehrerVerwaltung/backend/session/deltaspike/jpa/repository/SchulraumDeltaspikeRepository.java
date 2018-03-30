package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Schulraum.class)
public interface SchulraumDeltaspikeRepository extends EntityRepository<com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Schulraum, Long> {
    List<com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Schulraum> findByBezeichnungLikeIgnoreCase(String bezeichnung);

}
