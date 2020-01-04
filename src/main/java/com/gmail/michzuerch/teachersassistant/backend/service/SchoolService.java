package com.gmail.michzuerch.teachersassistant.backend.service;

import com.gmail.michzuerch.teachersassistant.backend.data.entity.School;
import com.gmail.michzuerch.teachersassistant.backend.data.entity.User;
import com.gmail.michzuerch.teachersassistant.backend.repositories.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.function.BiConsumer;

@Service
public class SchoolService implements CrudService<School> {

    private final SchoolRepository schoolRepository;

    @Autowired
    public SchoolService(SchoolRepository schoolRepository) {
        super();
        this.schoolRepository = schoolRepository;
    }

    @Transactional(rollbackOn = Exception.class)
    public School saveSchool(User currentUser, Long id, BiConsumer<User, School> schoolFiller) {
        School school;
        if (id == null) {
            school = new School();
        } else {
            school = load(id);
        }
        schoolFiller.accept(currentUser, school);
        return schoolRepository.save(school);
    }

    @Transactional(rollbackOn = Exception.class)
    public School saveSchool(School school) {
        return schoolRepository.save(school);
    }

    @Override
    public JpaRepository<School, Long> getRepository() {
        return schoolRepository;
    }

    @Override
    @Transactional
    public School createNew(User currentUser) {
        School school = new School();
        return school;
    }
}
