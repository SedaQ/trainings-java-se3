package com.trainings.service.iface;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;
import com.trainings.jpa.model.Role;

/**
 * 
 * @author Pavel Šeda
 *
 */
public interface RoleService {

	Optional<Role> findById(Long id);

	Page<Role> findAll(Predicate predicate, Pageable pageable);

	void create(Role role);

	void update(Role role);

	void delete(Role delete);

	void delete(Long id);

}
