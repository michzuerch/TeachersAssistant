package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository;


import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.ImageTest;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = ImageTest.class)
public interface ImageTestDeltaspikeRepository extends EntityRepository<ImageTest, Long> {
    List<ImageTest> findByTitelLikeIgnoreCase(String titel);
}
