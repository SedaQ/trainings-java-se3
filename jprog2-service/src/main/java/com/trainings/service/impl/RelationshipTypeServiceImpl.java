package com.trainings.service.impl;

import java.util.Optional;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;
import com.trainings.jpa.model.RelationshipType;
import com.trainings.jpa.repository.RelationshipTypeRepository;
import com.trainings.service.exceptions.ServiceLayerException;
import com.trainings.service.iface.RelationshipTypeService;

/**
 * 
 * @author Pavel Šeda
 *
 */
@Service
public class RelationshipTypeServiceImpl implements RelationshipTypeService {

	private RelationshipTypeRepository relationshipTypeRepository;

	@Autowired
	public RelationshipTypeServiceImpl(RelationshipTypeRepository relationshipTypeRepository) {
		this.relationshipTypeRepository = relationshipTypeRepository;
	}

	@Override
	public Optional<RelationshipType> findById(Long id) {
		try {
			return relationshipTypeRepository.findById(id);
		} catch (HibernateException ex) {
			throw new ServiceLayerException(ex);
		}
	}

	@Override
	public Page<RelationshipType> findAll(Predicate predicate, Pageable pageable) {
		try {
			return relationshipTypeRepository.findAll(predicate, pageable);
		} catch (HibernateException ex) {
			throw new ServiceLayerException(ex);
		}
	}

	@Override
	public void create(RelationshipType relationshipType) {
		try {
			relationshipTypeRepository.save(relationshipType);
		} catch (HibernateException ex) {
			throw new ServiceLayerException(ex);
		}
	}

	@Override
	public void update(RelationshipType relationshipType) {
		try {
			relationshipTypeRepository.save(relationshipType);
		} catch (HibernateException ex) {
			throw new ServiceLayerException(ex);
		}
	}

	@Override
	public void delete(RelationshipType relationshipType) {
		try {
			relationshipTypeRepository.delete(relationshipType);
		} catch (HibernateException ex) {
			throw new ServiceLayerException(ex);
		}
	}

	@Override
	public void delete(Long id) {
		try {
			relationshipTypeRepository.deleteById(id);
		} catch (HibernateException ex) {
			throw new ServiceLayerException(ex);
		}
	}

}
