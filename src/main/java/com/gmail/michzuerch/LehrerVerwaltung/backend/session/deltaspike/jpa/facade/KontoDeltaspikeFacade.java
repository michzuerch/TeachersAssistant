package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Konto;
import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Kontogruppe;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository.KontoDeltaspikeRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class KontoDeltaspikeFacade {
    @Inject
    KontoDeltaspikeRepository repo;

    public Konto findBy(Long id) {
        return repo.findBy(id);
    }

    public void delete(Konto val) {
        repo.attachAndRemove(val);
    }

    public Konto save(Konto val) {
        return repo.save(val);
    }

    public List<Konto> findAll() {
        return repo.findAll();
    }

    public List<Konto> findByBezeichnungLikeIgnoreCase(String bezeichnung) {
        return repo.findByBezeichnungLikeIgnoreCase(bezeichnung);
    }

    public List<Konto> findByKontogruppe(Kontogruppe kontogruppe) {
        return repo.findByKontogruppe(kontogruppe);
    }

}
