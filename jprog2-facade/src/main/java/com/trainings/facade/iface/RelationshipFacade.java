package com.trainings.facade.iface;

import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;
import com.trainings.facade.api.PageResultResource;
import com.trainings.facade.api.dto.relationship.RelationshipCreateDTO;
import com.trainings.facade.api.dto.relationship.RelationshipDTO;

/**
 * 
 * @author Pavel Šeda
 *
 */
public interface RelationshipFacade {

	RelationshipDTO findById(Long id);

	PageResultResource<RelationshipDTO> findAll(Predicate predicate, Pageable pageable);

	RelationshipCreateDTO create(RelationshipCreateDTO relationshipDTO);

	RelationshipDTO update(RelationshipDTO relationshipDTO);

	void delete(RelationshipDTO relationshipDTO);

	void delete(Long id);

}
