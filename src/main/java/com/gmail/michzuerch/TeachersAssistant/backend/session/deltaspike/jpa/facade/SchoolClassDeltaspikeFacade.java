package com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.facade;


import com.gmail.michzuerch.TeachersAssistant.backend.entity.School;
import com.gmail.michzuerch.TeachersAssistant.backend.entity.SchoolClass;
import com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.repository.SchoolClassDeltaspikeRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class SchoolClassDeltaspikeFacade {
    @Inject
    SchoolClassDeltaspikeRepository repo;

    public List<SchoolClass> findAll() {
        return repo.findAll();
    }

    public SchoolClass findBy(Long id) {
        return repo.findBy(id);
    }

    public void delete(SchoolClass val) {
        repo.attachAndRemove(val);
    }

    public SchoolClass save(SchoolClass val) {
        return repo.save(val);
    }

    public List<SchoolClass> findByBezeichungLikeIgnoreCase(String bezeichnung) {
        return repo.findByBezeichnungLikeIgnoreCase(bezeichnung);
    }

    public List<SchoolClass> findBySchule(School school) {
        return repo.findBySchule(school);
    }
}
