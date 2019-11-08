package com.gmail.michzuerch.teachersassistant.backend.service;

import java.util.Optional;

import com.gmail.michzuerch.teachersassistant.backend.repositories.PickupLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.gmail.michzuerch.teachersassistant.backend.data.entity.PickupLocation;
import com.gmail.michzuerch.teachersassistant.backend.data.entity.User;

@Service
public class PickupLocationService implements FilterableCrudService<PickupLocation>{

	private final PickupLocationRepository pickupLocationRepository;

	@Autowired
	public PickupLocationService(PickupLocationRepository pickupLocationRepository) {
		this.pickupLocationRepository = pickupLocationRepository;
	}

	public Page<PickupLocation> findAnyMatching(Optional<String> filter, Pageable pageable) {
		if (filter.isPresent()) {
			String repositoryFilter = "%" + filter.get() + "%";
			return pickupLocationRepository.findByNameLikeIgnoreCase(repositoryFilter, pageable);
		} else {
			return pickupLocationRepository.findAll(pageable);
		}
	}

	public long countAnyMatching(Optional<String> filter) {
		if (filter.isPresent()) {
			String repositoryFilter = "%" + filter.get() + "%";
			return pickupLocationRepository.countByNameLikeIgnoreCase(repositoryFilter);
		} else {
			return pickupLocationRepository.count();
		}
	}

	public PickupLocation getDefault() {
		return findAnyMatching(Optional.empty(), PageRequest.of(0, 1)).iterator().next();
	}

	@Override
	public JpaRepository<PickupLocation, Long> getRepository() {
		return pickupLocationRepository;
	}

	@Override
	public PickupLocation createNew(User currentUser) {
		return new PickupLocation();
	}
}
