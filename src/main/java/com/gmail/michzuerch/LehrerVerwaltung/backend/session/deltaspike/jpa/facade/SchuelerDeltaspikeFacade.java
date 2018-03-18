package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade;


import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Klasse;
import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Schueler;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository.SchuelerDeltaspikeRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class SchuelerDeltaspikeFacade {
    @Inject
    SchuelerDeltaspikeRepository repo;

    public List<Schueler> findAll() {
        return repo.findAll();
    }

    public Schueler findBy(Long id) {
        return repo.findBy(id);
    }

    public void delete(Schueler val) {
        repo.attachAndRemove(val);
    }

    public Schueler save(Schueler val) {
        return repo.save(val);
    }

    public List<Schueler> findByNachnameLikeIgnoreCase(String nachname) {
        return repo.findByNachnameLikeIgnoreCase(nachname);
    }

    public List<Schueler> findByKlasse(Klasse klasse) {
        return repo.findByKlasse(klasse);
    }
}
