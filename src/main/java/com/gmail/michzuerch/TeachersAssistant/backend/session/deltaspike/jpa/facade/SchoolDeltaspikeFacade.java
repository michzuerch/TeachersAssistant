package com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.facade;


import com.gmail.michzuerch.TeachersAssistant.backend.entity.School;
import com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.repository.SchoolDeltaspikeRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class SchoolDeltaspikeFacade {
    @Inject
    SchoolDeltaspikeRepository repo;

    public List<School> findAll() {
        return repo.findAll();
    }

    public School findBy(Long id) {
        return repo.findBy(id);
    }

    public void delete(School val) {
        repo.attachAndRemove(val);
    }

    public School save(School val) {
        return repo.save(val);
    }

    public List<School> findByBezeichungLikeIgnoreCase(String bezeichnung) {
        return repo.findByBezeichnungLikeIgnoreCase(bezeichnung);
    }
}
