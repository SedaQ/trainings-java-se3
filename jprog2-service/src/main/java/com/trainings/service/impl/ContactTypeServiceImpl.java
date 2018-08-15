package com.trainings.service.impl;

import java.util.Optional;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;
import com.trainings.jpa.model.ContactType;
import com.trainings.jpa.repository.ContactTypeRepository;
import com.trainings.service.exceptions.ServiceLayerException;
import com.trainings.service.iface.ContactTypeService;

/**
 * 
 * @author Pavel Šeda
 *
 */
@Service
public class ContactTypeServiceImpl implements ContactTypeService {

	private ContactTypeRepository contactTypeRepository;

	@Autowired
	public ContactTypeServiceImpl(ContactTypeRepository contactTypeRepository) {
		this.contactTypeRepository = contactTypeRepository;
	}

	@Override
	public Optional<ContactType> findById(Long id) {
		try {
			return contactTypeRepository.findById(id);
		} catch (HibernateException ex) {
			throw new ServiceLayerException(ex);
		}
	}

	@Override
	public Page<ContactType> findAll(Predicate predicate, Pageable pageable) {
		try {
			return contactTypeRepository.findAll(predicate, pageable);
		} catch (HibernateException ex) {
			throw new ServiceLayerException(ex);
		}
	}

	@Override
	public void create(ContactType contactType) {
		try {
			contactTypeRepository.save(contactType);
		} catch (HibernateException ex) {
			throw new ServiceLayerException(ex);
		}
	}

	@Override
	public void update(ContactType contactType) {
		try {
			contactTypeRepository.save(contactType);
		} catch (HibernateException ex) {
			throw new ServiceLayerException(ex);
		}
	}

	@Override
	public void delete(ContactType contactType) {
		try {
			contactTypeRepository.delete(contactType);
		} catch (HibernateException ex) {
			throw new ServiceLayerException(ex);
		}
	}

	@Override
	public void delete(Long id) {
		try {
			contactTypeRepository.deleteById(id);
		} catch (HibernateException ex) {
			throw new ServiceLayerException(ex);
		}
	}

}
