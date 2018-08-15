package com.trainings.facade.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;
import com.trainings.facade.api.PageResultResource;
import com.trainings.facade.api.dto.address.AddressCreateDTO;
import com.trainings.facade.api.dto.address.AddressDTO;
import com.trainings.facade.exceptions.FacadeLayerException;
import com.trainings.facade.iface.AddressFacade;
import com.trainings.facade.mapping.BeanMapping;
import com.trainings.jpa.model.Address;
import com.trainings.service.exceptions.ServiceLayerException;
import com.trainings.service.iface.AddressService;

/**
 * 
 * @author Pavel Šeda
 *
 */
@Service
@Transactional
public class AddressFacadeImpl implements AddressFacade {

	private AddressService addressService;
	private BeanMapping beanMapping;

	@Autowired
	public AddressFacadeImpl(AddressService addressService, BeanMapping beanMapping) {
		this.addressService = addressService;
		this.beanMapping = beanMapping;
	}

	@Override
	@Transactional(readOnly = true)
	public AddressDTO findById(Long id) {
		try {
			Optional<Address> addressOpt = addressService.findById(id);
			Address address = addressOpt
					.orElseThrow(() -> new ServiceLayerException("Address with this id is not found"));
			return beanMapping.mapTo(address, AddressDTO.class);
		} catch (NullPointerException ex) {
			throw new FacadeLayerException("Given Address ID is null.");
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public PageResultResource<AddressDTO> findAll(Predicate predicate, Pageable pageable) {
		try {
			return beanMapping.mapToPageResource(addressService.findAll(predicate, pageable), AddressDTO.class);
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	public AddressCreateDTO create(AddressCreateDTO addressCreateDTO) {
		try {
			addressService.create(beanMapping.mapTo(addressCreateDTO, Address.class));
			return addressCreateDTO;
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	public AddressDTO update(AddressDTO addressDTO) {
		try {
			addressService.update(beanMapping.mapTo(addressDTO, Address.class));
			return addressDTO;
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	public void delete(AddressDTO addressDTO) {
		try {
			addressService.delete(beanMapping.mapTo(addressDTO, Address.class));
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	public void delete(Long id) {
		try {
			addressService.delete(id);
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

}
