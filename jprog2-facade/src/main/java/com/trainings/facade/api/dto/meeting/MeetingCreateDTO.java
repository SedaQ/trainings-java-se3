package com.trainings.facade.api.dto.meeting;

import java.time.LocalDateTime;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;

/**
 * 
 * @author Pavel Šeda
 *
 */
public class MeetingCreateDTO {

	@Future
	private LocalDateTime meetingDateTime;
	@NotEmpty
	private String place;
	private String note;

	public MeetingCreateDTO() {
	}

	public LocalDateTime getMeetingDateTime() {
		return meetingDateTime;
	}

	public void setMeetingDateTime(LocalDateTime meetingDateTime) {
		this.meetingDateTime = meetingDateTime;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
