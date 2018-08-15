package com.trainings.service.iface;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;
import com.trainings.jpa.model.Relationship;

/**
 * 
 * @author Pavel Šeda
 *
 */
public interface RelationshipService {

	Optional<Relationship> findById(Long id);

	Page<Relationship> findAll(Predicate predicate, Pageable pageable);

	void create(Relationship relationship);

	void update(Relationship relationship);

	void delete(Relationship delete);

	void delete(Long id);

}
