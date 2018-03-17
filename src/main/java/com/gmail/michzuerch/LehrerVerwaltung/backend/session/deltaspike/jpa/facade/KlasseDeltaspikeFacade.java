package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade;


import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Klasse;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository.KlasseDeltaspikeRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class KlasseDeltaspikeFacade {
    @Inject
    KlasseDeltaspikeRepository repo;

    public List<Klasse> findAll() {
        return repo.findAll();
    }

    public Klasse findBy(Long id) {
        return repo.findBy(id);
    }

    public void delete(Klasse val) {
        repo.attachAndRemove(val);
    }

    public Klasse save(Klasse val) {
        return repo.save(val);
    }

    public List<Klasse> findByBezeichungLikeIgnoreCase(String bezeichnung) {
        return repo.findByBezeichnungLikeIgnoreCase(bezeichnung);
    }
}
