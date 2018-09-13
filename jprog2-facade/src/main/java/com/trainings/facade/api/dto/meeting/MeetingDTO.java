package com.trainings.facade.api.dto.meeting;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.trainings.facade.api.dto.person.MeetingPersonsDTO;

/**
 * 
 * @author Pavel Šeda
 *
 */
@JacksonXmlRootElement(localName = "meeting")
public class MeetingDTO {

	private Long idMeeting;
	private LocalDateTime startTime;
	private Duration duration;
	private String place;
	private String note;
	private Set<MeetingPersonsDTO> persons = new HashSet<>();

	public MeetingDTO() {}
	
	public MeetingDTO(Long idMeeting, LocalDateTime startTime, Duration duration, String place, String note,
			Set<MeetingPersonsDTO> persons) {
		super();
		this.idMeeting = idMeeting;
		this.startTime = startTime;
		this.duration = duration;
		this.place = place;
		this.note = note;
		this.persons = persons;
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

	public Set<MeetingPersonsDTO> getPersons() {
		return persons;
	}

	public void setPersons(Set<MeetingPersonsDTO> persons) {
		this.persons = new HashSet<>(persons);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MeetingDTO [idMeeting=");
		builder.append(idMeeting);
		builder.append(", startTime=");
		builder.append(startTime);
		builder.append(", duration=");
		builder.append(duration);
		builder.append(", place=");
		builder.append(place);
		builder.append(", note=");
		builder.append(note);
		builder.append(", persons=");
		builder.append(persons);
		builder.append("]");
		return builder.toString();
	}

}
