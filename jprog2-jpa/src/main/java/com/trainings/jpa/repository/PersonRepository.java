package com.trainings.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.trainings.jpa.model.Person;

/**
 * 
 * @author Pavel Šeda
 *
 */
public interface PersonRepository extends JpaRepository<Person, Long>, QuerydslPredicateExecutor<Person> {

}
