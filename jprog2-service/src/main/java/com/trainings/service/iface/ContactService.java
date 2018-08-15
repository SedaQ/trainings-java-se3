package com.trainings.service.iface;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;
import com.trainings.jpa.model.Contact;

/**
 * 
 * @author Pavel Šeda
 *
 */
public interface ContactService {

	Optional<Contact> findById(Long id);

	Page<Contact> findAll(Predicate predicate, Pageable pageable);

	void create(Contact contact);

	void update(Contact contact);

	void delete(Contact delete);

	void delete(Long id);

}
