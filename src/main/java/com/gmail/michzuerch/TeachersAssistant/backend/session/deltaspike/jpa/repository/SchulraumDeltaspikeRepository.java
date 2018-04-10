package com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.repository;

import com.gmail.michzuerch.TeachersAssistant.backend.entity.Classroom;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = Classroom.class)
public interface SchulraumDeltaspikeRepository extends EntityRepository<Classroom, Long> {
    List<Classroom> findByBezeichnungLikeIgnoreCase(String bezeichnung);

}
