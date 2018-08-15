package com.trainings.facade.iface;

import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;
import com.trainings.facade.api.PageResultResource;
import com.trainings.facade.api.dto.contact.ContactTypeCreateDTO;
import com.trainings.facade.api.dto.contact.ContactTypeDTO;

/**
 * 
 * @author Pavel Šeda
 *
 */
public interface ContactTypeFacade {

	ContactTypeDTO findById(Long id);

	PageResultResource<ContactTypeDTO> findAll(Predicate predicate, Pageable pageable);

	ContactTypeCreateDTO create(ContactTypeCreateDTO contactTypeDTO);

	ContactTypeDTO update(ContactTypeDTO contactTypeDTO);

	void delete(ContactTypeDTO contactTypeDTO);

	void delete(Long id);

}
