package com.gmail.michzuerch.teachersassistant.backend.service;

import com.gmail.michzuerch.teachersassistant.backend.data.entity.SchoolSubject;
import com.gmail.michzuerch.teachersassistant.backend.data.entity.User;
import com.gmail.michzuerch.teachersassistant.backend.repositories.SchoolSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.function.BiConsumer;

@Service
public class SchoolSubjectService implements CrudService<SchoolSubject> {

    private final SchoolSubjectRepository schoolSubjectRepository;

    @Autowired
    public SchoolSubjectService(SchoolSubjectRepository schoolSubjectRepository) {
        super();
        this.schoolSubjectRepository = schoolSubjectRepository;
    }

    @Transactional(rollbackOn = Exception.class)
    public SchoolSubject saveSchoolSubject(User currentUser, Long id, BiConsumer<User, SchoolSubject> schoolSubjectFiller) {
        SchoolSubject schoolSubject;
        if (id == null) {
            schoolSubject = new SchoolSubject();
        } else {
            schoolSubject = load(id);
        }
        schoolSubjectFiller.accept(currentUser, schoolSubject);
        return schoolSubjectRepository.save(schoolSubject);
    }

    @Transactional(rollbackOn = Exception.class)
    public SchoolSubject saveSchoolSubject(SchoolSubject schoolSubject) {
        return schoolSubjectRepository.save(schoolSubject);
    }

    @Override
    public JpaRepository<SchoolSubject, Long> getRepository() {
        return schoolSubjectRepository;
    }

    @Override
    @Transactional
    public SchoolSubject createNew(User currentUser) {
        SchoolSubject schoolSubject = new SchoolSubject();
        return schoolSubject;
    }
}
