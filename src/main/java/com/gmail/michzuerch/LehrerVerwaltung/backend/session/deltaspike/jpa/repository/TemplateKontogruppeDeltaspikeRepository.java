package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository;


import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.TemplateKontogruppe;
import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.TemplateKontohauptgruppe;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = TemplateKontogruppe.class)
public interface TemplateKontogruppeDeltaspikeRepository extends EntityRepository<TemplateKontogruppe, Long> {
    List<TemplateKontogruppe> findByBezeichnungLikeIgnoreCase(String bezeichnung);

    List<TemplateKontogruppe> findByTemplateKontohauptgruppe(TemplateKontohauptgruppe templateKontohauptgruppe);

}
