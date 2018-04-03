package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Lektion.class)
public interface LektionDeltaspikeRepository extends EntityRepository<com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Lektion, Long> {
    List<com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Lektion> findByBezeichnungLikeIgnoreCase(String bezeichnung);

}
