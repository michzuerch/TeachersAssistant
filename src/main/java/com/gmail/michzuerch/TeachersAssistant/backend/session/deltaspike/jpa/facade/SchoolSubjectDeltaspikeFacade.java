package com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.facade;


import com.gmail.michzuerch.TeachersAssistant.backend.entity.SchoolSubject;
import com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.repository.SchoolSubjectDeltaspikeRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class SchoolSubjectDeltaspikeFacade {
    @Inject
    SchoolSubjectDeltaspikeRepository repo;

    public List<SchoolSubject> findAll() {
        return repo.findAll();
    }

    public SchoolSubject findBy(Long id) {
        return repo.findBy(id);
    }

    public void delete(SchoolSubject val) {
        repo.attachAndRemove(val);
    }

    public SchoolSubject save(SchoolSubject val) {
        return repo.save(val);
    }

    public List<SchoolSubject> findByBezeichungLikeIgnoreCase(String bezeichnung) {
        return repo.findByBezeichnungLikeIgnoreCase(bezeichnung);
    }
}
