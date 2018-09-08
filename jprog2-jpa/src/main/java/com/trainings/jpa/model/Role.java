package com.trainings.jpa.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 
 * @author Pavel Šeda
 *
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_role", updatable = false, nullable = false)
	private Long idRole;
	@Column(nullable = false)
	private String title;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "person_has_role", joinColumns = { @JoinColumn(name = "id_role") }, inverseJoinColumns = {
			@JoinColumn(name = "id_person") })
	private Set<Person> persons = new HashSet<>();

	public Role() {
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

	public Set<Person> getUsers() {
		return Collections.unmodifiableSet(persons);
	}

	public void setUsers(Set<Person> persons) {
		this.persons = new HashSet<>(persons);
	}

	public void addUser(Person person) {
		this.persons.add(person);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Role [idRole=");
		builder.append(idRole);
		builder.append(", title=");
		builder.append(title);
		builder.append(", persons=");
		builder.append(persons);
		builder.append("]");
		return builder.toString();
	}

}
