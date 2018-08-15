package com.trainings.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.trainings.jpa.model.Meeting;

/**
 * 
 * @author Pavel Šeda
 *
 */
public interface MeetingRepository extends JpaRepository<Meeting, Long>, QuerydslPredicateExecutor<Meeting> {

}
