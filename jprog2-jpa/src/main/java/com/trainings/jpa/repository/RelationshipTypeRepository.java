package com.trainings.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.trainings.jpa.model.RelationshipType;

/**
 * 
 * @author Pavel Šeda
 *
 */
public interface RelationshipTypeRepository
		extends JpaRepository<RelationshipType, Long>, QuerydslPredicateExecutor<RelationshipType> {

}
