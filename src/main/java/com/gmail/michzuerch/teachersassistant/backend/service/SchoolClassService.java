package com.gmail.michzuerch.teachersassistant.backend.service;

import com.gmail.michzuerch.teachersassistant.backend.data.entity.SchoolClass;
import com.gmail.michzuerch.teachersassistant.backend.data.entity.User;
import com.gmail.michzuerch.teachersassistant.backend.repositories.SchoolClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.function.BiConsumer;

@Service
public class SchoolClassService implements CrudService<SchoolClass> {

    private final SchoolClassRepository schoolClassRepository;

    @Autowired
    public SchoolClassService(SchoolClassRepository schoolClassRepository) {
        super();
        this.schoolClassRepository = schoolClassRepository;
    }

    @Transactional(rollbackOn = Exception.class)
    public SchoolClass saveSchoolClass(User currentUser, Long id, BiConsumer<User, SchoolClass> schoolClassFiller) {
        SchoolClass schoolClass;
        if (id == null) {
            schoolClass = new SchoolClass();
        } else {
            schoolClass = load(id);
        }
        schoolClassFiller.accept(currentUser, schoolClass);
        return schoolClassRepository.save(schoolClass);
    }

    @Transactional(rollbackOn = Exception.class)
    public SchoolClass saveSchoolClass(SchoolClass schoolClass) {
        return schoolClassRepository.save(schoolClass);
    }

    @Override
    public JpaRepository<SchoolClass, Long> getRepository() {
        return schoolClassRepository;
    }

    @Override
    @Transactional
    public SchoolClass createNew(User currentUser) {
        SchoolClass schoolClass = new SchoolClass();
        return schoolClass;
    }
}
