package com.trainings.facade.iface;

import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;
import com.trainings.facade.api.PageResultResource;
import com.trainings.facade.api.dto.relationship.RelationshipTypeCreateDTO;
import com.trainings.facade.api.dto.relationship.RelationshipTypeDTO;

/**
 * 
 * @author Pavel Šeda
 *
 */
public interface RelationshipTypeFacade {

	RelationshipTypeDTO findById(Long id);

	PageResultResource<RelationshipTypeDTO> findAll(Predicate predicate, Pageable pageable);

	RelationshipTypeCreateDTO create(RelationshipTypeCreateDTO relationshipTypeDTO);

	RelationshipTypeDTO update(RelationshipTypeDTO relationshipTypeDTO);

	void delete(RelationshipTypeDTO relationshipTypeDTO);

	void delete(Long id);

}
