package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository;


import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.TemplateBuchhaltung;
import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.TemplateKontoklasse;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = TemplateKontoklasse.class)
public interface TemplateKontoklasseDeltaspikeRepository extends EntityRepository<TemplateKontoklasse, Long> {
    List<TemplateKontoklasse> findByBezeichnungLikeIgnoreCase(String bezeichnung);

    List<TemplateKontoklasse> findByTemplateBuchhaltung(TemplateBuchhaltung templateBuchhaltung);
}
