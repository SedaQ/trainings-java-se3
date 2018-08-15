package com.trainings.service.impl;

import java.util.Optional;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;
import com.trainings.jpa.model.Address;
import com.trainings.jpa.repository.AddressRepository;
import com.trainings.service.exceptions.ServiceLayerException;
import com.trainings.service.iface.AddressService;

/**
 * 
 * @author Pavel Šeda
 *
 */
@Service
public class AddressServiceImpl implements AddressService {

	private AddressRepository addressRepository;

	@Autowired
	public AddressServiceImpl(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	@Override
	public Optional<Address> findById(Long id) {
		try {
			return addressRepository.findById(id);
		} catch (HibernateException ex) {
			throw new ServiceLayerException(ex);
		}
	}

	@Override
	public Page<Address> findAll(Predicate predicate, Pageable pageable) {
		try {
			return addressRepository.findAll(predicate, pageable);
		} catch (HibernateException ex) {
			throw new ServiceLayerException(ex);
		}
	}

	@Override
	public void create(Address address) {
		try {
			addressRepository.save(address);
		} catch (HibernateException ex) {
			throw new ServiceLayerException(ex);
		}
	}

	@Override
	public void update(Address address) {
		try {
			addressRepository.save(address);
		} catch (HibernateException ex) {
			throw new ServiceLayerException(ex);
		}
	}

	@Override
	public void delete(Address address) {
		try {
			addressRepository.delete(address);
		} catch (HibernateException ex) {
			throw new ServiceLayerException(ex);
		}
	}

	@Override
	public void delete(Long id) {
		try {
			addressRepository.deleteById(id);
		} catch (HibernateException ex) {
			throw new ServiceLayerException(ex);
		}
	}

}
