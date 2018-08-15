package com.trainings.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.trainings.jpa.model.ContactType;

/**
 * 
 * @author Pavel Šeda
 *
 */
public interface ContactTypeRepository
		extends JpaRepository<ContactType, Long>, QuerydslPredicateExecutor<ContactType> {

}
