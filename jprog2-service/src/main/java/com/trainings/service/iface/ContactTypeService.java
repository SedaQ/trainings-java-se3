package com.trainings.service.iface;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;
import com.trainings.jpa.model.ContactType;

/**
 * 
 * @author Pavel Šeda
 *
 */
public interface ContactTypeService {

	Optional<ContactType> findById(Long id);

	Page<ContactType> findAll(Predicate predicate, Pageable pageable);

	void create(ContactType contactType);

	void update(ContactType contactType);

	void delete(ContactType delete);

	void delete(Long id);

}
