package com.trainings.service.impl;

import java.util.Optional;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;
import com.trainings.jpa.model.Relationship;
import com.trainings.jpa.repository.RelationshipRepository;
import com.trainings.service.exceptions.ServiceLayerException;
import com.trainings.service.iface.RelationshipService;

/**
 * 
 * @author Pavel Šeda
 *
 */
@Service
public class RelationshipServiceImpl implements RelationshipService {

	private RelationshipRepository relationshipRepository;

	@Autowired
	public RelationshipServiceImpl(RelationshipRepository relationshipRepository) {
		this.relationshipRepository = relationshipRepository;
	}

	@Override
	public Optional<Relationship> findById(Long id) {
		try {
			return relationshipRepository.findById(id);
		} catch (HibernateException ex) {
			throw new ServiceLayerException(ex);
		}
	}

	@Override
	public Page<Relationship> findAll(Predicate predicate, Pageable pageable) {
		try {
			return relationshipRepository.findAll(predicate, pageable);
		} catch (HibernateException ex) {
			throw new ServiceLayerException(ex);
		}
	}

	@Override
	public void create(Relationship relationship) {
		try {
			relationshipRepository.save(relationship);
		} catch (HibernateException ex) {
			throw new ServiceLayerException(ex);
		}
	}

	@Override
	public void update(Relationship relationship) {
		try {
			relationshipRepository.save(relationship);
		} catch (HibernateException ex) {
			throw new ServiceLayerException(ex);
		}
	}

	@Override
	public void delete(Relationship relationship) {
		try {
			relationshipRepository.delete(relationship);
		} catch (HibernateException ex) {
			throw new ServiceLayerException(ex);
		}
	}

	@Override
	public void delete(Long id) {
		try {
			relationshipRepository.deleteById(id);
		} catch (HibernateException ex) {
			throw new ServiceLayerException(ex);
		}
	}

}
