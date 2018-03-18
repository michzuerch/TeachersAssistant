package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade;


import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Lehrer;
import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Schule;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository.LehrerDeltaspikeRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class LehrerDeltaspikeFacade {
    @Inject
    LehrerDeltaspikeRepository repo;

    public List<Lehrer> findAll() {
        return repo.findAll();
    }

    public Lehrer findBy(Long id) {
        return repo.findBy(id);
    }

    public void delete(Lehrer val) {
        repo.attachAndRemove(val);
    }

    public Lehrer save(Lehrer val) {
        return repo.save(val);
    }

    public List<Lehrer> findByNachnameLikeIgnoreCase(String nachname) {
        return repo.findByNachnameLikeIgnoreCase(nachname);
    }

    public List<Lehrer> findBySchule(Schule schule) {
        return repo.findBySchule(schule);
    }
}
