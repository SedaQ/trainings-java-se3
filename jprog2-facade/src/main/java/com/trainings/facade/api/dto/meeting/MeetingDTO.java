package com.trainings.facade.api.dto.meeting;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.trainings.facade.api.dto.user.MeetingUsersDTO;

/**
 * 
 * @author Pavel Šeda
 *
 */
public class MeetingDTO {

	private Long idMeeting;
	private LocalDateTime meetingDateTime;
	private String place;
	private String note;
	private Set<MeetingUsersDTO> users = new HashSet<>();

	public MeetingDTO() {
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

	public Set<MeetingUsersDTO> getUsers() {
		return users;
	}

	public void setUsers(Set<MeetingUsersDTO> users) {
		this.users = users;
	}

}
