package com.trainings.facade.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;
import com.trainings.facade.api.PageResultResource;
import com.trainings.facade.api.dto.contact.ContactCreateDTO;
import com.trainings.facade.api.dto.contact.ContactDTO;
import com.trainings.facade.exceptions.FacadeLayerException;
import com.trainings.facade.iface.ContactFacade;
import com.trainings.facade.mapping.BeanMapping;
import com.trainings.jpa.model.Contact;
import com.trainings.service.exceptions.ServiceLayerException;
import com.trainings.service.iface.ContactService;

/**
 * 
 * @author Pavel Šeda
 *
 */
@Service
@Transactional
public class ContactFacadeImpl implements ContactFacade {

	private ContactService contactService;
	private BeanMapping beanMapping;

	@Autowired
	public ContactFacadeImpl(ContactService contactService, BeanMapping beanMapping) {
		this.contactService = contactService;
		this.beanMapping = beanMapping;
	}

	@Override
	@Transactional(readOnly = true)
	public ContactDTO findById(Long id) {
		try {
			Optional<Contact> contactOpt = contactService.findById(id);
			Contact contact = contactOpt
					.orElseThrow(() -> new ServiceLayerException("Contact with this id is not found"));
			return beanMapping.mapTo(contact, ContactDTO.class);
		} catch (NullPointerException ex) {
			throw new FacadeLayerException("Given Contact ID is null.");
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public PageResultResource<ContactDTO> findAll(Predicate predicate, Pageable pageable) {
		try {
			return beanMapping.mapToPageResource(contactService.findAll(predicate, pageable), ContactDTO.class);
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	public ContactCreateDTO create(ContactCreateDTO contactDTO) {
		try {
			contactService.create(beanMapping.mapTo(contactDTO, Contact.class));
			return contactDTO;
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	public ContactDTO update(ContactDTO contactDTO) {
		try {
			contactService.update(beanMapping.mapTo(contactDTO, Contact.class));
			return contactDTO;
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	public void delete(ContactDTO contactDTO) {
		try {
			contactService.delete(beanMapping.mapTo(contactDTO, Contact.class));
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	public void delete(Long id) {
		try {
			contactService.delete(id);
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

}
