package com.trainings.facade.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;
import com.trainings.facade.api.PageResultResource;
import com.trainings.facade.api.dto.user.UserCreateDTO;
import com.trainings.facade.api.dto.user.UserDTO;
import com.trainings.facade.exceptions.FacadeLayerException;
import com.trainings.facade.iface.UserFacade;
import com.trainings.facade.mapping.BeanMapping;
import com.trainings.jpa.model.User;
import com.trainings.service.exceptions.ServiceLayerException;
import com.trainings.service.iface.UserService;

/**
 * 
 * @author Pavel Šeda
 *
 */
@Service
@Transactional
public class UserFacadeImpl implements UserFacade {

	private UserService userService;
	private BeanMapping beanMapping;

	@Autowired
	public UserFacadeImpl(UserService userService, BeanMapping beanMapping) {
		this.userService = userService;
		this.beanMapping = beanMapping;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		try {
			Optional<User> userOpt = userService.findById(id);
			User user = userOpt.orElseThrow(() -> new ServiceLayerException("User with this id is not found"));
			return beanMapping.mapTo(user, UserDTO.class);
		} catch (NullPointerException ex) {
			throw new FacadeLayerException("Given User ID is null.");
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public PageResultResource<UserDTO> findAll(Predicate predicate, Pageable pageable) {
		try {
			return beanMapping.mapToPageResource(userService.findAll(predicate, pageable), UserDTO.class);
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	public UserCreateDTO create(UserCreateDTO userDTO) {
		try {
			userService.create(beanMapping.mapTo(userDTO, User.class));
			return userDTO;
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	public UserDTO update(UserDTO userDTO) {
		try {
			userService.update(beanMapping.mapTo(userDTO, User.class));
			return userDTO;
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	public void delete(UserDTO userDTO) {
		try {
			userService.delete(beanMapping.mapTo(userDTO, User.class));
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	public void delete(Long id) {
		try {
			userService.delete(id);
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

}
