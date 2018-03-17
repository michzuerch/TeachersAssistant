package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Uzer;
import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.UzerRole;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository.UzerRoleDeltaspikeRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class UzerRoleDeltaspikeFacade {
    @Inject
    UzerRoleDeltaspikeRepository repo;

    public List<UzerRole> findAll() {
        return repo.findAll();
    }

    public UzerRole findBy(Long id) {
        return repo.findBy(id);
    }

    public void delete(UzerRole val) {
        repo.attachAndRemove(val);
    }

    public UzerRole save(UzerRole val) {
        return repo.save(val);
    }

    public List<UzerRole> findByUzerAndRoleLikeIgnoreCase(Uzer uzer, String role) {
        return repo.findByUzerAndRoleLikeIgnoreCase(uzer, role);
    }

    public List<UzerRole> findByUzer(Uzer uzer) {
        return repo.findByUzer(uzer);
    }

    public List<UzerRole> findByRoleLikeIgnoreCase(String role) {
        return repo.findByRoleLikeIgnoreCase(role);
    }

}
