package com.trainings.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.trainings.jpa.model.Address;

/**
 * 
 * @author Pavel Šeda
 *
 */
public interface AddressRepository extends JpaRepository<Address, Long>, QuerydslPredicateExecutor<Address> {

}
