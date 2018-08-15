package com.trainings.facade.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;
import com.trainings.facade.api.PageResultResource;
import com.trainings.facade.api.dto.relationship.RelationshipTypeCreateDTO;
import com.trainings.facade.api.dto.relationship.RelationshipTypeDTO;
import com.trainings.facade.exceptions.FacadeLayerException;
import com.trainings.facade.iface.RelationshipTypeFacade;
import com.trainings.facade.mapping.BeanMapping;
import com.trainings.jpa.model.RelationshipType;
import com.trainings.service.exceptions.ServiceLayerException;
import com.trainings.service.iface.RelationshipTypeService;

/**
 * 
 * @author Pavel Šeda
 *
 */
@Service
@Transactional
public class RelationshipTypeFacadeImpl implements RelationshipTypeFacade {

	private RelationshipTypeService relationshipTypeService;
	private BeanMapping beanMapping;

	@Autowired
	public RelationshipTypeFacadeImpl(RelationshipTypeService relationshipTypeService, BeanMapping beanMapping) {
		this.relationshipTypeService = relationshipTypeService;
		this.beanMapping = beanMapping;
	}

	@Override
	@Transactional(readOnly = true)
	public RelationshipTypeDTO findById(Long id) {
		try {
			Optional<RelationshipType> relationshipTypeOpt = relationshipTypeService.findById(id);
			RelationshipType relationshipType = relationshipTypeOpt
					.orElseThrow(() -> new ServiceLayerException("RelationshipType with this id is not found"));
			return beanMapping.mapTo(relationshipType, RelationshipTypeDTO.class);
		} catch (NullPointerException ex) {
			throw new FacadeLayerException("Given RelationshipType ID is null.");
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public PageResultResource<RelationshipTypeDTO> findAll(Predicate predicate, Pageable pageable) {
		try {
			return beanMapping.mapToPageResource(relationshipTypeService.findAll(predicate, pageable),
					RelationshipTypeDTO.class);
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	public RelationshipTypeCreateDTO create(RelationshipTypeCreateDTO relationshipTypeDTO) {
		try {
			relationshipTypeService.create(beanMapping.mapTo(relationshipTypeDTO, RelationshipType.class));
			return relationshipTypeDTO;
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	public RelationshipTypeDTO update(RelationshipTypeDTO relationshipTypeDTO) {
		try {
			relationshipTypeService.update(beanMapping.mapTo(relationshipTypeDTO, RelationshipType.class));
			return relationshipTypeDTO;
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	public void delete(RelationshipTypeDTO relationshipTypeDTO) {
		try {
			relationshipTypeService.delete(beanMapping.mapTo(relationshipTypeDTO, RelationshipType.class));
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	public void delete(Long id) {
		try {
			relationshipTypeService.delete(id);
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

}
