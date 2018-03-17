package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade;


import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.ImageTest;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository.ImageTestDeltaspikeRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ImageTestDeltaspikeFacade {
    @Inject
    ImageTestDeltaspikeRepository repo;

    public List<ImageTest> findAll() {
        return repo.findAll();
    }

    public ImageTest findBy(Long id) {
        return repo.findBy(id);
    }

    public void delete(ImageTest val) {
        repo.attachAndRemove(val);
    }

    public ImageTest save(ImageTest val) {
        return repo.save(val);
    }

    public List<ImageTest> findByTitelLikeIgnoreCase(String titel) {
        return repo.findByTitelLikeIgnoreCase(titel);
    }
}
