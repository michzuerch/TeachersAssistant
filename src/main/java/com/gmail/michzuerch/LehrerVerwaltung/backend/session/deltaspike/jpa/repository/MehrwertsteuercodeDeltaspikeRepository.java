package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository;


import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Buchhaltung;
import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Mehrwertsteuercode;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = Mehrwertsteuercode.class)
public interface MehrwertsteuercodeDeltaspikeRepository extends EntityRepository<Mehrwertsteuercode, Long> {
    List<Mehrwertsteuercode> findByBuchhaltung(Buchhaltung buchhaltung);

    List<Mehrwertsteuercode> findByBezeichnungLikeIgnoreCase(String bezeichnung);
}
