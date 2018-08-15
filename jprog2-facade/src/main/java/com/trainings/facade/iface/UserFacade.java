package com.trainings.facade.iface;

import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;
import com.trainings.facade.api.PageResultResource;
import com.trainings.facade.api.dto.user.UserCreateDTO;
import com.trainings.facade.api.dto.user.UserDTO;

/**
 * 
 * @author Pavel Šeda
 *
 */
public interface UserFacade {

	UserDTO findById(Long id);

	PageResultResource<UserDTO> findAll(Predicate predicate, Pageable pageable);

	UserCreateDTO create(UserCreateDTO user);

	UserDTO update(UserDTO user);

	void delete(UserDTO user);

	void delete(Long id);

}
