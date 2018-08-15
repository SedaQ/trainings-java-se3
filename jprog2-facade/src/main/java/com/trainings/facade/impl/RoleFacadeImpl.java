package com.trainings.facade.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;
import com.trainings.facade.api.PageResultResource;
import com.trainings.facade.api.dto.role.RoleCreateDTO;
import com.trainings.facade.api.dto.role.RoleDTO;
import com.trainings.facade.exceptions.FacadeLayerException;
import com.trainings.facade.iface.RoleFacade;
import com.trainings.facade.mapping.BeanMapping;
import com.trainings.jpa.model.Role;
import com.trainings.service.exceptions.ServiceLayerException;
import com.trainings.service.iface.RoleService;

/**
 * 
 * @author Pavel Šeda
 *
 */
@Service
@Transactional
public class RoleFacadeImpl implements RoleFacade {

	private RoleService roleService;
	private BeanMapping beanMapping;

	@Autowired
	public RoleFacadeImpl(RoleService roleService, BeanMapping beanMapping) {
		this.roleService = roleService;
		this.beanMapping = beanMapping;
	}

	@Override
	@Transactional(readOnly = true)
	public RoleDTO findById(Long id) {
		try {
			Optional<Role> roleOpt = roleService.findById(id);
			Role role = roleOpt.orElseThrow(() -> new ServiceLayerException("Role with this id is not found"));
			return beanMapping.mapTo(role, RoleDTO.class);
		} catch (NullPointerException ex) {
			throw new FacadeLayerException("Given Role ID is null.");
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public PageResultResource<RoleDTO> findAll(Predicate predicate, Pageable pageable) {
		try {
			return beanMapping.mapToPageResource(roleService.findAll(predicate, pageable), RoleDTO.class);
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	public RoleCreateDTO create(RoleCreateDTO roleCreateDTO) {
		try {
			roleService.create(beanMapping.mapTo(roleCreateDTO, Role.class));
			return roleCreateDTO;
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	public RoleDTO update(RoleDTO roleDTO) {
		try {
			roleService.update(beanMapping.mapTo(roleDTO, Role.class));
			return roleDTO;
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	public void delete(RoleDTO roleDTO) {
		try {
			roleService.delete(beanMapping.mapTo(roleDTO, Role.class));
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	public void delete(Long id) {
		try {
			roleService.delete(id);
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

}
