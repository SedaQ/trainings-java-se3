package com.trainings.facade.api.dto.role;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.trainings.facade.api.dto.user.RoleUsersDTO;

/**
 * 
 * @author Pavel Šeda
 *
 */
@JacksonXmlRootElement(localName="role")
public class RoleDTO {

	private Long idRole;
	private String title;
	private Set<RoleUsersDTO> users = new HashSet<>();

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

	public Set<RoleUsersDTO> getUsers() {
		return users;
	}

	public void setUsers(Set<RoleUsersDTO> users) {
		this.users = users;
	}

}
