package com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.repository;

import com.gmail.michzuerch.TeachersAssistant.backend.entity.SchoolSubject;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = SchoolSubject.class)
public interface SchoolSubjectDeltaspikeRepository extends EntityRepository<SchoolSubject, Long> {
    List<SchoolSubject> findByBezeichnungLikeIgnoreCase(String bezeichnung);

}
