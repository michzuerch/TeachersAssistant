package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Schule;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Klasse.class)
public interface KlasseDeltaspikeRepository extends EntityRepository<com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Klasse, Long> {
    List<com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Klasse> findByBezeichnungLikeIgnoreCase(String bezeichnung);

    List<com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Klasse> findBySchule(Schule schule);
}
