package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Schule.class)
public interface SchuleDeltaspikeRepository extends EntityRepository<com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Schule, Long> {
    List<com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Schule> findByBezeichnungLikeIgnoreCase(String bezeichnung);

    List<com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Schule> findByOrtLikeIgnoreCase(String ort);
}
