package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository;


import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.TemplateBuchhaltung;
import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.TemplateKonto;
import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.TemplateKontogruppe;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = TemplateKonto.class)
public interface TemplateKontoDeltaspikeRepository extends EntityRepository<TemplateKonto, Long> {
    List<TemplateKonto> findByBezeichnungLikeIgnoreCase(String bezeichnung);

    List<TemplateKonto> findByTemplateBuchhaltung(TemplateBuchhaltung templateBuchhaltung);

    List<TemplateKonto> findByTemplateKontogruppe(TemplateKontogruppe templateKontogruppe);
}
