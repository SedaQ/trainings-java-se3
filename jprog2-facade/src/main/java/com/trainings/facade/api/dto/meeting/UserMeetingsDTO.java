package com.trainings.facade.api.dto.meeting;

import java.time.LocalDateTime;

/**
 * 
 * @author Pavel Šeda
 *
 */
public class UserMeetingsDTO {

	private Long idMeeting;
	private LocalDateTime meetingDateTime;
	private String place;
	private String note;

	public UserMeetingsDTO() {
	}

	public Long getIdMeeting() {
		return idMeeting;
	}

	public void setIdMeeting(Long idMeeting) {
		this.idMeeting = idMeeting;
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
