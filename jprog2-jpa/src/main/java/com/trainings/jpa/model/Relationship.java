package com.trainings.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author Pavel Šeda
 *
 */
@Entity
@Table(name = "relationship")
public class Relationship implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_relationship")
	private Long idRelationship;
	@Column(nullable = true, length = 200)
	private String note;
	@ManyToOne
	@JoinColumn(name = "id_relationship_type")
	private RelationshipType relationshipType;
	@ManyToOne
	@JoinColumn(name = "id_user1")
	private User user1;
	@ManyToOne
	@JoinColumn(name = "id_user2")
	private User user2;

	public Relationship() {
	}

	public Long getIdRelationship() {
		return idRelationship;
	}

	public void setIdRelationship(Long idRelationship) {
		this.idRelationship = idRelationship;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public RelationshipType getRelationshipType() {
		return relationshipType;
	}

	public void setRelationshipType(RelationshipType relationshipType) {
		this.relationshipType = relationshipType;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Relationship [idRelationship=");
		builder.append(idRelationship);
		builder.append(", note=");
		builder.append(note);
		builder.append(", relationshipType=");
		builder.append(relationshipType);
		builder.append(", user1=");
		builder.append(user1);
		builder.append(", user2=");
		builder.append(user2);
		builder.append("]");
		return builder.toString();
	}

}
