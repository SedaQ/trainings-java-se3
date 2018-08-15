package com.trainings.facade.iface;

import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;
import com.trainings.facade.api.PageResultResource;
import com.trainings.facade.api.dto.contact.ContactCreateDTO;
import com.trainings.facade.api.dto.contact.ContactDTO;

/**
 * 
 * @author Pavel Šeda
 *
 */
public interface ContactFacade {

	ContactDTO findById(Long id);

	PageResultResource<ContactDTO> findAll(Predicate predicate, Pageable pageable);

	ContactCreateDTO create(ContactCreateDTO contactDTO);

	ContactDTO update(ContactDTO contactDTO);

	void delete(ContactDTO contactDTO);

	void delete(Long id);

}
