package com.trainings.facade.iface;

import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;
import com.trainings.facade.api.PageResultResource;
import com.trainings.facade.api.dto.address.AddressCreateDTO;
import com.trainings.facade.api.dto.address.AddressDTO;

/**
 * 
 * @author Pavel Šeda
 *
 */
public interface AddressFacade {

	AddressDTO findById(Long id);

	PageResultResource<AddressDTO> findAll(Predicate predicate, Pageable pageable);

	AddressCreateDTO create(AddressCreateDTO address);

	AddressDTO update(AddressDTO address);

	void delete(AddressDTO delete);

	void delete(Long id);

}
