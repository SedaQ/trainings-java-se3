package com.trainings.facade.iface;

import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;
import com.trainings.facade.api.PageResultResource;
import com.trainings.facade.api.dto.role.RoleCreateDTO;
import com.trainings.facade.api.dto.role.RoleDTO;

/**
 * 
 * @author Pavel Šeda
 *
 */
public interface RoleFacade {

	RoleDTO findById(Long id);

	PageResultResource<RoleDTO> findAll(Predicate predicate, Pageable pageable);

	RoleCreateDTO create(RoleCreateDTO roleCreateDTO);

	RoleDTO update(RoleDTO roleDTO);

	void delete(RoleDTO roleDTO);

	void delete(Long id);

}
