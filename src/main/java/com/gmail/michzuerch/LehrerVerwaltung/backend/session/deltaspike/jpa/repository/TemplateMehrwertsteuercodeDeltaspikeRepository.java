package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository;


import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.TemplateBuchhaltung;
import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.TemplateKonto;
import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.TemplateMehrwertsteuercode;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = TemplateMehrwertsteuercode.class)
public interface TemplateMehrwertsteuercodeDeltaspikeRepository extends EntityRepository<TemplateMehrwertsteuercode, Long> {
    List<TemplateMehrwertsteuercode> findByTemplateBuchhaltung(TemplateBuchhaltung templateBuchhaltung);

    List<TemplateMehrwertsteuercode> findByBezeichnungLikeIgnoreCase(String bezeichnung);

    TemplateMehrwertsteuercode findByTemplateKonto(TemplateKonto templateKonto);
}
