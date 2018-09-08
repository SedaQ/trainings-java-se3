package com.trainings.facade.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;
import com.trainings.facade.api.PageResultResource;
import com.trainings.facade.api.dto.person.PersonCreateDTO;
import com.trainings.facade.api.dto.person.PersonDTO;
import com.trainings.facade.exceptions.FacadeLayerException;
import com.trainings.facade.iface.PersonFacade;
import com.trainings.facade.mapping.BeanMapping;
import com.trainings.jpa.model.Person;
import com.trainings.service.exceptions.ServiceLayerException;
import com.trainings.service.iface.PersonService;

/**
 * 
 * @author Pavel Šeda
 *
 */
@Service
@Transactional
public class PersonFacadeImpl implements PersonFacade {

	private PersonService personService;
	private BeanMapping beanMapping;

	@Autowired
	public PersonFacadeImpl(PersonService personService, BeanMapping beanMapping) {
		this.personService = personService;
		this.beanMapping = beanMapping;
	}

	@Override
	@Transactional(readOnly = true)
	public PersonDTO findById(Long id) {
		try {
			Optional<Person> userOpt = personService.findById(id);
			Person user = userOpt.orElseThrow(() -> new ServiceLayerException("Person with this id is not found"));
			return beanMapping.mapTo(user, PersonDTO.class);
		} catch (NullPointerException ex) {
			throw new FacadeLayerException("Given Person ID is null.");
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public PageResultResource<PersonDTO> findAll(Predicate predicate, Pageable pageable) {
		try {
			return beanMapping.mapToPageResource(personService.findAll(predicate, pageable), PersonDTO.class);
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	public PersonCreateDTO create(PersonCreateDTO personDTO) {
		try {
			personService.create(beanMapping.mapTo(personDTO, Person.class));
			return personDTO;
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	public PersonDTO update(PersonDTO personDTO) {
		try {
			personService.update(beanMapping.mapTo(personDTO, Person.class));
			return personDTO;
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	public void delete(PersonDTO personDTO) {
		try {
			personService.delete(beanMapping.mapTo(personDTO, Person.class));
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	public void delete(Long id) {
		try {
			personService.delete(id);
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

}