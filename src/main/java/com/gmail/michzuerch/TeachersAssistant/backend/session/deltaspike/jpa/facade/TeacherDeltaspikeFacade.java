package com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.facade;


import com.gmail.michzuerch.TeachersAssistant.backend.entity.School;
import com.gmail.michzuerch.TeachersAssistant.backend.entity.Teacher;
import com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.repository.TeacherDeltaspikeRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class TeacherDeltaspikeFacade {
    @Inject
    TeacherDeltaspikeRepository repo;

    public List<Teacher> findAll() {
        return repo.findAll();
    }

    public Teacher findBy(Long id) {
        return repo.findBy(id);
    }

    public void delete(Teacher val) {
        repo.attachAndRemove(val);
    }

    public Teacher save(Teacher val) {
        return repo.save(val);
    }

    public List<Teacher> findByNachnameLikeIgnoreCase(String nachname) {
        return repo.findByNachnameLikeIgnoreCase(nachname);
    }

    public List<Teacher> findBySchule(School school) {
        return repo.findBySchule(school);
    }
}
