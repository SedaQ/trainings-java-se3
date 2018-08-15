package com.trainings.facade.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;
import com.trainings.facade.api.PageResultResource;
import com.trainings.facade.api.dto.contact.ContactTypeCreateDTO;
import com.trainings.facade.api.dto.contact.ContactTypeDTO;
import com.trainings.facade.exceptions.FacadeLayerException;
import com.trainings.facade.iface.ContactTypeFacade;
import com.trainings.facade.mapping.BeanMapping;
import com.trainings.jpa.model.ContactType;
import com.trainings.service.exceptions.ServiceLayerException;
import com.trainings.service.iface.ContactTypeService;

/**
 * 
 * @author Pavel Šeda
 *
 */
@Service
@Transactional
public class ContactTypeFacadeImpl implements ContactTypeFacade {

	private ContactTypeService contactTypeService;
	private BeanMapping beanMapping;

	@Autowired
	public ContactTypeFacadeImpl(ContactTypeService contactTypeService, BeanMapping beanMapping) {
		this.contactTypeService = contactTypeService;
		this.beanMapping = beanMapping;
	}

	@Override
	@Transactional(readOnly = true)
	public ContactTypeDTO findById(Long id) {
		try {
			Optional<ContactType> contactTypeOpt = contactTypeService.findById(id);
			ContactType contactType = contactTypeOpt
					.orElseThrow(() -> new ServiceLayerException("ContactType with this id is not found"));
			return beanMapping.mapTo(contactType, ContactTypeDTO.class);
		} catch (NullPointerException ex) {
			throw new FacadeLayerException("Given ContactType ID is null.");
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public PageResultResource<ContactTypeDTO> findAll(Predicate predicate, Pageable pageable) {
		try {
			return beanMapping.mapToPageResource(contactTypeService.findAll(predicate, pageable), ContactTypeDTO.class);
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	public ContactTypeCreateDTO create(ContactTypeCreateDTO contactTypeDTO) {
		try {
			contactTypeService.create(beanMapping.mapTo(contactTypeDTO, ContactType.class));
			return contactTypeDTO;
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	public ContactTypeDTO update(ContactTypeDTO contactTypeDTO) {
		try {
			contactTypeService.update(beanMapping.mapTo(contactTypeDTO, ContactType.class));
			return contactTypeDTO;
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	public void delete(ContactTypeDTO contactTypeDTO) {
		try {
			contactTypeService.delete(beanMapping.mapTo(contactTypeDTO, ContactType.class));
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	public void delete(Long id) {
		try {
			contactTypeService.delete(id);
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

}
