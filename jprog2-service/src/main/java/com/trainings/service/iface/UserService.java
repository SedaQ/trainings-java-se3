package com.trainings.service.iface;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;
import com.trainings.jpa.model.User;

/**
 * 
 * @author Pavel Šeda
 *
 */
public interface UserService {

	Optional<User> findById(Long id);

	Page<User> findAll(Predicate predicate, Pageable pageable);

	void create(User user);

	void update(User user);

	void delete(User delete);

	void delete(Long id);

}
