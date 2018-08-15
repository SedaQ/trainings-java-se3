package com.trainings.facade.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;
import com.trainings.facade.api.PageResultResource;
import com.trainings.facade.api.dto.relationship.RelationshipCreateDTO;
import com.trainings.facade.api.dto.relationship.RelationshipDTO;
import com.trainings.facade.exceptions.FacadeLayerException;
import com.trainings.facade.iface.RelationshipFacade;
import com.trainings.facade.mapping.BeanMapping;
import com.trainings.jpa.model.Relationship;
import com.trainings.service.exceptions.ServiceLayerException;
import com.trainings.service.iface.RelationshipService;

/**
 * 
 * @author Pavel Šeda
 *
 */
@Service
@Transactional
public class RelationshipFacadeImpl implements RelationshipFacade {

	private RelationshipService relationshipService;
	private BeanMapping beanMapping;

	@Autowired
	public RelationshipFacadeImpl(RelationshipService relationshipService, BeanMapping beanMapping) {
		this.relationshipService = relationshipService;
		this.beanMapping = beanMapping;
	}

	@Override
	@Transactional(readOnly = true)
	public RelationshipDTO findById(Long id) {
		try {
			Optional<Relationship> relationshipOpt = relationshipService.findById(id);
			Relationship relationship = relationshipOpt
					.orElseThrow(() -> new ServiceLayerException("Relationship with this id is not found"));
			return beanMapping.mapTo(relationship, RelationshipDTO.class);
		} catch (NullPointerException ex) {
			throw new FacadeLayerException("Given Relationship ID is null.");
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public PageResultResource<RelationshipDTO> findAll(Predicate predicate, Pageable pageable) {
		try {
			return beanMapping.mapToPageResource(relationshipService.findAll(predicate, pageable),
					RelationshipDTO.class);
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	public RelationshipCreateDTO create(RelationshipCreateDTO relationshipDTO) {
		try {
			relationshipService.create(beanMapping.mapTo(relationshipDTO, Relationship.class));
			return relationshipDTO;
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	public RelationshipDTO update(RelationshipDTO relationshipDTO) {
		try {
			relationshipService.update(beanMapping.mapTo(relationshipDTO, Relationship.class));
			return relationshipDTO;
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	public void delete(RelationshipDTO relationshipDTO) {
		try {
			relationshipService.delete(beanMapping.mapTo(relationshipDTO, Relationship.class));
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	public void delete(Long id) {
		try {
			relationshipService.delete(id);
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

}
