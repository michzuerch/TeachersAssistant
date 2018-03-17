package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade;


import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Schule;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository.SchuleDeltaspikeRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class SchuleDeltaspikeFacade {
    @Inject
    SchuleDeltaspikeRepository repo;

    public List<Schule> findAll() {
        return repo.findAll();
    }

    public Schule findBy(Long id) {
        return repo.findBy(id);
    }

    public void delete(Schule val) {
        repo.attachAndRemove(val);
    }

    public Schule save(Schule val) {
        return repo.save(val);
    }

    public List<Schule> findByBezeichungLikeIgnoreCase(String bezeichnung) {
        return repo.findByBezeichnungLikeIgnoreCase(bezeichnung);
    }
}
