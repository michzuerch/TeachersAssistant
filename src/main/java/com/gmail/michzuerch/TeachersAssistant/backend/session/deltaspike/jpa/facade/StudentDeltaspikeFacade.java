package com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.facade;


import com.gmail.michzuerch.TeachersAssistant.backend.entity.SchoolClass;
import com.gmail.michzuerch.TeachersAssistant.backend.entity.Student;
import com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.repository.StudentDeltaspikeRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class StudentDeltaspikeFacade {
    @Inject
    StudentDeltaspikeRepository repo;

    public List<Student> findAll() {
        return repo.findAll();
    }

    public Student findBy(Long id) {
        return repo.findBy(id);
    }

    public void delete(Student val) {
        repo.attachAndRemove(val);
    }

    public Student save(Student val) {
        return repo.save(val);
    }

    public List<Student> findByNachnameLikeIgnoreCase(String nachname) {
        return repo.findByNachnameLikeIgnoreCase(nachname);
    }

    public List<Student> findByKlasse(SchoolClass aSchoolClass) {
        return repo.findByKlasse(aSchoolClass);
    }
}
