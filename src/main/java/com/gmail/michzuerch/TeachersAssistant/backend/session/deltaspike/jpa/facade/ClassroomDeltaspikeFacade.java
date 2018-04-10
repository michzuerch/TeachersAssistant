package com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.facade;


import com.gmail.michzuerch.TeachersAssistant.backend.entity.Classroom;
import com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.repository.SchulraumDeltaspikeRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ClassroomDeltaspikeFacade {
    @Inject
    SchulraumDeltaspikeRepository repo;

    public List<Classroom> findAll() {
        return repo.findAll();
    }

    public Classroom findBy(Long id) {
        return repo.findBy(id);
    }

    public void delete(Classroom val) {
        repo.attachAndRemove(val);
    }

    public Classroom save(Classroom val) {
        return repo.save(val);
    }

    public List<Classroom> findByBezeichungLikeIgnoreCase(String bezeichnung) {
        return repo.findByBezeichnungLikeIgnoreCase(bezeichnung);
    }
}
