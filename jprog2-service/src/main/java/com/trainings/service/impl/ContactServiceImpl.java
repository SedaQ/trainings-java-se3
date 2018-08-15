package com.trainings.service.impl;

import java.util.Optional;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;
import com.trainings.jpa.model.Contact;
import com.trainings.jpa.repository.ContactRepository;
import com.trainings.service.exceptions.ServiceLayerException;
import com.trainings.service.iface.ContactService;

/**
 * 
 * @author Pavel Šeda
 *
 */
@Service
public class ContactServiceImpl implements ContactService {

	private ContactRepository contactRepository;

	@Autowired
	public ContactServiceImpl(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}

	@Override
	public Optional<Contact> findById(Long id) {
		try {
			return contactRepository.findById(id);
		} catch (HibernateException ex) {
			throw new ServiceLayerException(ex);
		}
	}

	@Override
	public Page<Contact> findAll(Predicate predicate, Pageable pageable) {
		try {
			return contactRepository.findAll(predicate, pageable);
		} catch (HibernateException ex) {
			throw new ServiceLayerException(ex);
		}
	}

	@Override
	public void create(Contact contact) {
		try {
			contactRepository.save(contact);
		} catch (HibernateException ex) {
			throw new ServiceLayerException(ex);
		}
	}

	@Override
	public void update(Contact contact) {
		try {
			contactRepository.save(contact);
		} catch (HibernateException ex) {
			throw new ServiceLayerException(ex);
		}
	}

	@Override
	public void delete(Contact contact) {
		try {
			contactRepository.delete(contact);
		} catch (HibernateException ex) {
			throw new ServiceLayerException(ex);
		}
	}

	@Override
	public void delete(Long id) {
		try {
			contactRepository.deleteById(id);
		} catch (HibernateException ex) {
			throw new ServiceLayerException(ex);
		}
	}

}
