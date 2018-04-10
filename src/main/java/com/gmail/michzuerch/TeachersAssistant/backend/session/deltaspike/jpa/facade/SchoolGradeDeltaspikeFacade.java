package com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.facade;


import com.gmail.michzuerch.TeachersAssistant.backend.entity.SchoolGrade;
import com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.repository.SchulnoteDeltaspikeRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class SchoolGradeDeltaspikeFacade {
    @Inject
    SchulnoteDeltaspikeRepository repo;

    public List<SchoolGrade> findAll() {
        return repo.findAll();
    }

    public SchoolGrade findBy(Long id) {
        return repo.findBy(id);
    }

    public void delete(SchoolGrade val) {
        repo.attachAndRemove(val);
    }

    public SchoolGrade save(SchoolGrade val) {
        return repo.save(val);
    }

    public List<SchoolGrade> findByBezeichungLikeIgnoreCase(String bezeichnung) {
        return repo.findByBezeichnungLikeIgnoreCase(bezeichnung);
    }
}
