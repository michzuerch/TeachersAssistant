package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository;


import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.TemplateBuchhaltung;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = TemplateBuchhaltung.class)
public interface TemplateBuchhaltungDeltaspikeRepository extends EntityRepository<TemplateBuchhaltung, Long> {
    List<TemplateBuchhaltung> findByBezeichnungLikeIgnoreCase(String bezeichnung);
}
