package com.trainings.facade.api.dto.meeting;

import java.time.Duration;
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
	private LocalDateTime startTime;
	@NotEmpty
	private String place;
	private String note;
	private Duration duration;

	public MeetingCreateDTO() {
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MeetingCreateDTO [startTime=");
		builder.append(startTime);
		builder.append(", place=");
		builder.append(place);
		builder.append(", note=");
		builder.append(note);
		builder.append(", duration=");
		builder.append(duration);
		builder.append("]");
		return builder.toString();
	}

}
