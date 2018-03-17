package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.EditorTest;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository.EditorTestDeltaspikeRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class EditorTestDeltaspikeFacade {
    @PersistenceContext
    public EntityManager em;
    @Inject
    EditorTestDeltaspikeRepository repo;


    public List<EditorTest> findAll() {
        return repo.findAll();
    }

    public EditorTest save(EditorTest value) {
        return repo.save(value);
    }

    public void delete(EditorTest e) {
        repo.attachAndRemove(e);
    }
}
