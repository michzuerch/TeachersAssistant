package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Buchhaltung;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository.BuchhaltungDeltaspikeRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class BuchhaltungDeltaspikeFacade {
    @Inject
    BuchhaltungDeltaspikeRepository repo;

    public Buchhaltung findBy(Long id) {
        return repo.findBy(id);
    }

    public void delete(Buchhaltung val) {
        repo.attachAndRemove(val);
    }

    public Buchhaltung save(Buchhaltung val) {
        return repo.save(val);
    }

    public List<Buchhaltung> findAll() {
        return repo.findAll();
    }

    public List<Buchhaltung> findByBezeichnungLikeIgnoreCase(String bezeichnung) {
        return repo.findByBezeichnungLikeIgnoreCase(bezeichnung);
    }

}
