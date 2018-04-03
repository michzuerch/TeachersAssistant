package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade;


import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Lektion;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository.LektionDeltaspikeRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class LektionDeltaspikeFacade {
    @Inject
    LektionDeltaspikeRepository repo;

    public List<Lektion> findAll() {
        return repo.findAll();
    }

    public Lektion findBy(Long id) {
        return repo.findBy(id);
    }

    public void delete(Lektion val) {
        repo.attachAndRemove(val);
    }

    public Lektion save(Lektion val) {
        return repo.save(val);
    }

    public List<Lektion> findByBezeichungLikeIgnoreCase(String bezeichnung) {
        return repo.findByBezeichnungLikeIgnoreCase(bezeichnung);
    }
}
