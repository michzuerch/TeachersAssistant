package com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.repository;

import com.gmail.michzuerch.TeachersAssistant.backend.entity.SchoolClass;
import com.gmail.michzuerch.TeachersAssistant.backend.entity.Student;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = Student.class)
public interface StudentDeltaspikeRepository extends EntityRepository<Student, Long> {
    List<Student> findByNachnameLikeIgnoreCase(String nachname);

    List<Student> findByKlasse(SchoolClass aSchoolClass);
}
