package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Aufwand;
import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Rechnung;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = Aufwand.class)
public interface AufwandDeltaspikeRepository extends EntityRepository<Aufwand, Long> {
    List<Aufwand> findByRechnung(Rechnung rechnung);

    List<Aufwand> findByRechnungAndTitelLikeIgnoreCase(Rechnung rechnung, String titel);

    List<Aufwand> findByTitelLikeIgnoreCase(String titel);

}
