package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade;


import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Schulraum;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository.SchulraumDeltaspikeRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class SchulraumDeltaspikeFacade {
    @Inject
    SchulraumDeltaspikeRepository repo;

    public List<Schulraum> findAll() {
        return repo.findAll();
    }

    public Schulraum findBy(Long id) {
        return repo.findBy(id);
    }

    public void delete(Schulraum val) {
        repo.attachAndRemove(val);
    }

    public Schulraum save(Schulraum val) {
        return repo.save(val);
    }

    public List<Schulraum> findByBezeichungLikeIgnoreCase(String bezeichnung) {
        return repo.findByBezeichnungLikeIgnoreCase(bezeichnung);
    }
}
