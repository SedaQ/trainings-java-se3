package com.trainings.facade.api.dto.person;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * 
 * @author Pavel Šeda
 *
 */
public class RolePersonsDTO {

	private Long idPerson;
	private String email;
	@JsonProperty(access = Access.WRITE_ONLY)
	private char[] pwd;
	private String nickname;
	private String firstName;
	private String surname;
	private LocalDate birthday;
	private Integer age;

	public RolePersonsDTO() {
	}

	public Long getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(Long idPerson) {
		this.idPerson = idPerson;
	}

	public char[] getPwd() {
		return pwd;
	}

	public void setPwd(char[] pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	public int hashCode() {
		return Objects.hash(email);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof PersonDTO))
			return false;
		PersonDTO other = (PersonDTO) obj;
		return Objects.equals(email, other.getEmail());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RolePersonsDTO [idPerson=");
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
		builder.append("]");
		return builder.toString();
	}

}
