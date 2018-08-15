package com.trainings.service.iface;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;
import com.trainings.jpa.model.RelationshipType;

/**
 * 
 * @author Pavel Šeda
 *
 */
public interface RelationshipTypeService {

	Optional<RelationshipType> findById(Long id);

	Page<RelationshipType> findAll(Predicate predicate, Pageable pageable);

	void create(RelationshipType relationshipType);

	void update(RelationshipType relationshipType);

	void delete(RelationshipType delete);

	void delete(Long id);

}
