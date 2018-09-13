package com.trainings.facade.api.dto.person;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.trainings.facade.api.enums.RoleDTO;

/**
 * 
 * @author Pavel Šeda
 *
 */
public class MeetingPersonsDTO {

	private Long idPerson;
	private String email;
	@JsonProperty(access = Access.WRITE_ONLY)
	private char[] pwd;
	private String nickname;
	private String firstName;
	private String surname;
	private LocalDate birthday;
	private Integer age;
	private Set<RoleDTO> roles = new HashSet<RoleDTO>();

	public MeetingPersonsDTO() {}
	
	public MeetingPersonsDTO(Long idPerson, String email, char[] pwd, String nickname, String firstName, String surname,
			LocalDate birthday, Integer age, Set<RoleDTO> roles) {
		super();
		this.idPerson = idPerson;
		this.email = email;
		this.pwd = pwd;
		this.nickname = nickname;
		this.firstName = firstName;
		this.surname = surname;
		this.birthday = birthday;
		this.age = age;
		this.roles = roles;
	}

	public Set<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDTO> roles) {
		this.roles = roles;
	}

	public Long getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(Long idPerson) {
		this.idPerson = idPerson;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public char[] getPwd() {
		return pwd;
	}

	public void setPwd(char[] pwd) {
		this.pwd = pwd;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MeetingPersonsDTO [idPerson=");
		builder.append(idPerson);
		builder.append(", email=");
		builder.append(email);
		builder.append(", pwd=");
		builder.append(Arrays.toString(pwd));
		builder.append(", nickname=");
		builder.append(nickname);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", surname=");
		builder.append(surname);
		builder.append(", birthday=");
		builder.append(birthday);
		builder.append(", age=");
		builder.append(age);
		builder.append(", roles=");
		builder.append(roles);
		builder.append("]");
		return builder.toString();
	}

}
