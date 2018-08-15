package com.trainings.service.iface;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;
import com.trainings.jpa.model.Address;

/**
 * 
 * @author Pavel Šeda
 *
 */
public interface AddressService {

	Optional<Address> findById(Long id);

	Page<Address> findAll(Predicate predicate, Pageable pageable);

	void create(Address address);

	void update(Address address);

	void delete(Address delete);

	void delete(Long id);

}
