package com.trainings.facade.api.dto.meeting;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * 
 * @author Pavel Šeda
 *
 */
public class PersonMeetingsDTO {

	private Long idMeeting;
	private LocalDateTime startTime;
	private Duration duration;
	private String place;
	private String note;

	public PersonMeetingsDTO() {
	}

	public Long getIdMeeting() {
		return idMeeting;
	}

	public void setIdMeeting(Long idMeeting) {
		this.idMeeting = idMeeting;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
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

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PersonMeetingsDTO [idMeeting=");
		builder.append(idMeeting);
		builder.append(", startTime=");
		builder.append(startTime);
		builder.append(", duration=");
		builder.append(duration);
		builder.append(", place=");
		builder.append(place);
		builder.append(", note=");
		builder.append(note);
		builder.append("]");
		return builder.toString();
	}

}
