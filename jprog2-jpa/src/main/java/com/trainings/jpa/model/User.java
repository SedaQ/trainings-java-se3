package com.trainings.jpa.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author Pavel Šeda
 *
 */
@Entity
@Table(name = "\"user\"")
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Long idUser;
	@Column(nullable = false, length = 100)
	private String email;
	@Column(nullable = false)
	private char[] password;
	@Column(nullable = false, length = 45)
	private String nickname;
	@Column(nullable = false, length = 45)
	private String firstName;
	@Column(nullable = false, length = 45)
	private String surname;
	@Column(nullable = true)
	private LocalDate birthday;
	@Column(nullable = true)
	private Integer age;
	@ManyToMany(fetch = FetchType.LAZY, targetEntity = Meeting.class, mappedBy = "users")
	private Set<Meeting> meetings = new HashSet<>();
	@ManyToMany(fetch = FetchType.LAZY, targetEntity = Role.class, mappedBy = "users")
	private Set<Role> roles = new HashSet<>();
	@ManyToOne
	@JoinColumn(name = "id_address")
	private Address address;
	@OneToMany(targetEntity = Contact.class, mappedBy = "user")
	private Set<Contact> contacts = new HashSet<>();
	@OneToMany(targetEntity = Relationship.class, mappedBy = "user1")
	private Set<User> users1 = new HashSet<>();
	@OneToMany(targetEntity = Relationship.class, mappedBy = "user2")
	private Set<User> users2 = new HashSet<>();

	public User() {
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
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

	public Set<Meeting> getMeetings() {
		return Collections.unmodifiableSet(meetings);
	}

	public void setMeetings(Set<Meeting> meetings) {
		this.meetings = meetings;
	}

	public void addMeeting(Meeting meeting) {
		this.meetings.add(meeting);
	}

	public Set<Role> getRoles() {
		return Collections.unmodifiableSet(roles);
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role role) {
		this.roles.add(role);
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<Contact> getContacts() {
		return Collections.unmodifiableSet(contacts);
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

	public void addContact(Contact contact) {
		this.contacts.add(contact);
	}

	public Set<User> getUsers1() {
		return Collections.unmodifiableSet(users1);
	}

	public void setUsers1(Set<User> users1) {
		this.users1 = users1;
	}

	public Set<User> getUsers2() {
		return Collections.unmodifiableSet(users2);
	}

	public void setUsers2(Set<User> users2) {
		this.users2 = users2;
	}

//	@Override
//	public int hashCode() {
//		return Objects.hashCode(email);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (!(obj instanceof User))
//			return false;
//		User other = (User) obj;
//		return Objects.equals(email, other.getEmail());
//	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [idUser=");
		builder.append(idUser);
		builder.append(", email=");
		builder.append(email);
		builder.append(", password=");
		builder.append(Arrays.toString(password));
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
		builder.append(", meetings=");
		builder.append(meetings);
		builder.append(", roles=");
		builder.append(roles);
		builder.append(", address=");
		builder.append(address);
		builder.append(", contacts=");
		builder.append(contacts);
		builder.append(", users1=");
		builder.append(users1);
		builder.append(", users2=");
		builder.append(users2);
		builder.append("]");
		return builder.toString();
	}

}
