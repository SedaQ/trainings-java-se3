package com.trainings.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.trainings.jpa.model.Relationship;

/**
 * 
 * @author Pavel Šeda
 *
 */
public interface RelationshipRepository
		extends JpaRepository<Relationship, Long>, QuerydslPredicateExecutor<Relationship> {

}
