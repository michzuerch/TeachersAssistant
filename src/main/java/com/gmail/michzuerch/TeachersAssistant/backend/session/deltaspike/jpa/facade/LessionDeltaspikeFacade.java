package com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.facade;


import com.gmail.michzuerch.TeachersAssistant.backend.entity.Lession;
import com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.repository.LessionDeltaspikeRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@Stateless
public class LessionDeltaspikeFacade {
    @Inject
    LessionDeltaspikeRepository repo;

    @Inject
    EntityManager entityManager;

    public List<Lession> findAll() {
        return repo.findAll();
    }

    public Lession findBy(Long id) {
        return repo.findBy(id);
    }

    public void delete(Lession val) {
        repo.attachAndRemove(val);
    }

    public Lession save(Lession val) {
        return repo.save(val);
    }

    public List<Lession> findByBezeichungLikeIgnoreCase(String bezeichnung) {
        return repo.findByBezeichnungLikeIgnoreCase(bezeichnung);
    }

    public List<Lession> findByStartAndEnd(LocalDateTime start, LocalDateTime end) {
        //TypedQuery<Lession> query = entityManager.createTypedQuery("SELECT l from Lession l where l.start between :start and :ende");
        return repo.findByStartAndEnd(start, end);
    }
}
