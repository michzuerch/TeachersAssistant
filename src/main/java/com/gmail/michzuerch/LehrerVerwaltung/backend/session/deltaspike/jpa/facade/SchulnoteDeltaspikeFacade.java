package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade;


import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Schulnote;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository.SchulnoteDeltaspikeRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class SchulnoteDeltaspikeFacade {
    @Inject
    SchulnoteDeltaspikeRepository repo;

    public List<Schulnote> findAll() {
        return repo.findAll();
    }

    public Schulnote findBy(Long id) {
        return repo.findBy(id);
    }

    public void delete(Schulnote val) {
        repo.attachAndRemove(val);
    }

    public Schulnote save(Schulnote val) {
        return repo.save(val);
    }

    public List<Schulnote> findByBezeichungLikeIgnoreCase(String bezeichnung) {
        return repo.findByBezeichnungLikeIgnoreCase(bezeichnung);
    }
}
