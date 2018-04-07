package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Lektion;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository(forEntity = com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Lektion.class)
public interface LektionDeltaspikeRepository extends EntityRepository<com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Lektion, Long> {
    List<com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Lektion> findByBezeichnungLikeIgnoreCase(String bezeichnung);

    @Query("select l from Lektion l where  l.start between :startDate and :endDate")
    List<Lektion> findByStartAndEnd(LocalDateTime start, LocalDateTime ende);

}
