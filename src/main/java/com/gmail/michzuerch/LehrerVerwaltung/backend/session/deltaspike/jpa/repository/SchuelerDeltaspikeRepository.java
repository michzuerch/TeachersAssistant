package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Schueler.class)
public interface SchuelerDeltaspikeRepository extends EntityRepository<com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Schueler, Long> {
    List<com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Schueler> findByNachnameLikeIgnoreCase(String nachname);
}
