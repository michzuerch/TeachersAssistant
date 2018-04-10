package com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.repository;

import com.gmail.michzuerch.TeachersAssistant.backend.entity.School;
import com.gmail.michzuerch.TeachersAssistant.backend.entity.SchoolClass;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = SchoolClass.class)
public interface SchoolClassDeltaspikeRepository extends EntityRepository<SchoolClass, Long> {
    List<SchoolClass> findByBezeichnungLikeIgnoreCase(String bezeichnung);

    List<SchoolClass> findBySchule(School school);
}
