package com.trainings.facade.api.dto.role;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.trainings.facade.api.dto.person.RolePersonsDTO;

/**
 * 
 * @author Pavel Šeda
 *
 */
@JacksonXmlRootElement(localName = "role")
public class RoleDTO {

	private Long idRole;
	private String title;
	private Set<RolePersonsDTO> persons = new HashSet<>();

	public RoleDTO() {
	}

	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<RolePersonsDTO> getPersons() {
		return persons;
	}

	public void setPersons(Set<RolePersonsDTO> persons) {
		this.persons = new HashSet<>(persons);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoleDTO [idRole=");
		builder.append(idRole);
		builder.append(", title=");
		builder.append(title);
		builder.append(", persons=");
		builder.append(persons);
		builder.append("]");
		return builder.toString();
	}

}
