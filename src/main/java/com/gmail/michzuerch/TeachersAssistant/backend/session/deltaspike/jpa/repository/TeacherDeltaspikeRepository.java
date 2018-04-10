package com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.repository;

import com.gmail.michzuerch.TeachersAssistant.backend.entity.School;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = com.gmail.michzuerch.TeachersAssistant.backend.entity.Teacher.class)
public interface TeacherDeltaspikeRepository extends EntityRepository<com.gmail.michzuerch.TeachersAssistant.backend.entity.Teacher, Long> {
    List<com.gmail.michzuerch.TeachersAssistant.backend.entity.Teacher> findByNachnameLikeIgnoreCase(String nachname);

    List<com.gmail.michzuerch.TeachersAssistant.backend.entity.Teacher> findBySchule(School school);
}
