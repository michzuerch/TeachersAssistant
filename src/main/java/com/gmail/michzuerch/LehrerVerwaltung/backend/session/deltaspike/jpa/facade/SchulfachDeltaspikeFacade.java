package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade;


import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Schulfach;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository.SchulfachDeltaspikeRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class SchulfachDeltaspikeFacade {
    @Inject
    SchulfachDeltaspikeRepository repo;

    public List<Schulfach> findAll() {
        return repo.findAll();
    }

    public Schulfach findBy(Long id) {
        return repo.findBy(id);
    }

    public void delete(Schulfach val) {
        repo.attachAndRemove(val);
    }

    public Schulfach save(Schulfach val) {
        return repo.save(val);
    }

    public List<Schulfach> findByBezeichungLikeIgnoreCase(String bezeichnung) {
        return repo.findByBezeichnungLikeIgnoreCase(bezeichnung);
    }
}
