package com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.repository;

import com.gmail.michzuerch.TeachersAssistant.backend.entity.School;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = School.class)
public interface SchoolDeltaspikeRepository extends EntityRepository<School, Long> {
    List<School> findByBezeichnungLikeIgnoreCase(String bezeichnung);

    List<School> findByOrtLikeIgnoreCase(String ort);
}
