package com.trainings.facade.iface;

import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;
import com.trainings.facade.api.PageResultResource;
import com.trainings.facade.api.dto.person.PersonCreateDTO;
import com.trainings.facade.api.dto.person.PersonDTO;

/**
 * 
 * @author Pavel Šeda
 *
 */
public interface PersonFacade {

	PersonDTO findById(Long id);

	PageResultResource<PersonDTO> findAll(Predicate predicate, Pageable pageable);

	PersonCreateDTO create(PersonCreateDTO person);

	PersonDTO update(PersonDTO person);

	void delete(PersonDTO person);

	void delete(Long id);

}
