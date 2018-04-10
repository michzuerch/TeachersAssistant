package com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.repository;

import com.gmail.michzuerch.TeachersAssistant.backend.entity.SchoolGrade;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = SchoolGrade.class)
public interface SchulnoteDeltaspikeRepository extends EntityRepository<SchoolGrade, Long> {
    List<SchoolGrade> findByBezeichnungLikeIgnoreCase(String bezeichnung);

}
