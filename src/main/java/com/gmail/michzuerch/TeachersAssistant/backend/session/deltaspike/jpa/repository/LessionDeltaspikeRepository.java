package com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.repository;

import com.gmail.michzuerch.TeachersAssistant.backend.entity.Lession;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository(forEntity = Lession.class)
public interface LessionDeltaspikeRepository extends EntityRepository<Lession, Long> {
    List<Lession> findByBezeichnungLikeIgnoreCase(String bezeichnung);

    @Query("select l from Lession l where  l.start between :startDate and :endDate")
    List<Lession> findByStartAndEnd(LocalDateTime start, LocalDateTime ende);

}
