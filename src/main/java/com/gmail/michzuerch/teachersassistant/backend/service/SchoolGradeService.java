package com.gmail.michzuerch.teachersassistant.backend.service;

import com.gmail.michzuerch.teachersassistant.backend.data.entity.SchoolGrade;
import com.gmail.michzuerch.teachersassistant.backend.data.entity.User;
import com.gmail.michzuerch.teachersassistant.backend.repositories.SchoolGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.function.BiConsumer;

@Service
public class SchoolGradeService implements CrudService<SchoolGrade> {

    private final SchoolGradeRepository schoolGradeRepository;

    @Autowired
    public SchoolGradeService(SchoolGradeRepository schoolGradeRepository) {
        super();
        this.schoolGradeRepository = schoolGradeRepository;
    }

    @Transactional(rollbackOn = Exception.class)
    public SchoolGrade saveSchoolGrade(User currentUser, Long id, BiConsumer<User, SchoolGrade> schoolGradeFiller) {
        SchoolGrade schoolGrade;
        if (id == null) {
            schoolGrade = new SchoolGrade();
        } else {
            schoolGrade = load(id);
        }
        schoolGradeFiller.accept(currentUser, schoolGrade);
        return schoolGradeRepository.save(schoolGrade);
    }

    @Transactional(rollbackOn = Exception.class)
    public SchoolGrade saveSchoolGrade(SchoolGrade schoolGrade) {
        return schoolGradeRepository.save(schoolGrade);
    }

    @Override
    public JpaRepository<SchoolGrade, Long> getRepository() {
        return schoolGradeRepository;
    }

    @Override
    @Transactional
    public SchoolGrade createNew(User currentUser) {
        SchoolGrade schoolGrade = new SchoolGrade();
        return schoolGrade;
    }
}
