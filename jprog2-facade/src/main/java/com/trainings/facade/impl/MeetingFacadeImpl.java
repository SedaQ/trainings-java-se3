package com.trainings.facade.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;
import com.trainings.facade.api.PageResultResource;
import com.trainings.facade.api.dto.meeting.MeetingCreateDTO;
import com.trainings.facade.api.dto.meeting.MeetingDTO;
import com.trainings.facade.exceptions.FacadeLayerException;
import com.trainings.facade.iface.MeetingFacade;
import com.trainings.facade.mapping.BeanMapping;
import com.trainings.jpa.model.Meeting;
import com.trainings.service.exceptions.ServiceLayerException;
import com.trainings.service.iface.MeetingService;

/**
 * 
 * @author Pavel Šeda
 *
 */
@Service
@Transactional
public class MeetingFacadeImpl implements MeetingFacade {

	private MeetingService meetingService;
	private BeanMapping beanMapping;

	@Autowired
	public MeetingFacadeImpl(MeetingService meetingService, BeanMapping beanMapping) {
		this.meetingService = meetingService;
		this.beanMapping = beanMapping;
	}

	@Override
	@Transactional(readOnly = true)
	public MeetingDTO findById(Long id) {
		try {
			Optional<Meeting> meetingOpt = meetingService.findById(id);
			Meeting meeting = meetingOpt
					.orElseThrow(() -> new ServiceLayerException("Meeting with this id is not found"));
			return beanMapping.mapTo(meeting, MeetingDTO.class);
		} catch (NullPointerException ex) {
			throw new FacadeLayerException("Given User ID is null.");
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public PageResultResource<MeetingDTO> findAll(Predicate predicate, Pageable pageable) {
		try {
			return beanMapping.mapToPageResource(meetingService.findAll(predicate, pageable), MeetingDTO.class);
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	public MeetingCreateDTO create(MeetingCreateDTO meetingDTO) {
		try {
			meetingService.create(beanMapping.mapTo(meetingDTO, Meeting.class));
			return meetingDTO;
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	public MeetingDTO update(MeetingDTO meetingDTO) {
		try {
			meetingService.update(beanMapping.mapTo(meetingDTO, Meeting.class));
			return meetingDTO;
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	public void delete(MeetingDTO meetingDTO) {
		try {
			meetingService.delete(beanMapping.mapTo(meetingDTO, Meeting.class));
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

	@Override
	public void delete(Long id) {
		try {
			meetingService.delete(id);
		} catch (ServiceLayerException ex) {
			throw new FacadeLayerException(ex);
		}
	}

}
