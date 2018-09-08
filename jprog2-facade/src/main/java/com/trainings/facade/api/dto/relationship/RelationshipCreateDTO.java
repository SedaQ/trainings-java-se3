package com.trainings.facade.api.dto.relationship;

import javax.validation.constraints.NotNull;

import com.trainings.facade.api.dto.person.PersonDTO;

/**
 * 
 * @author Pavel Šeda
 *
 */
public class RelationshipCreateDTO {

	private String note;
	@NotNull
	private RelationshipTypeDTO relationshipType;
	@NotNull
	private PersonDTO person1;
	@NotNull
	private PersonDTO person2;

	public RelationshipCreateDTO() {
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public RelationshipTypeDTO getRelationshipType() {
		return relationshipType;
	}

	public void setRelationshipType(RelationshipTypeDTO relationshipType) {
		this.relationshipType = relationshipType;
	}

	public PersonDTO getPerson1() {
		return person1;
	}

	public void setPerson1(PersonDTO person1) {
		this.person1 = person1;
	}

	public PersonDTO getPerson2() {
		return person2;
	}

	public void setPerson2(PersonDTO person2) {
		this.person2 = person2;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RelationshipCreateDTO [note=");
		builder.append(note);
		builder.append(", relationshipType=");
		builder.append(relationshipType);
		builder.append(", person1=");
		builder.append(person1);
		builder.append(", person2=");
		builder.append(person2);
		builder.append("]");
		return builder.toString();
	}

}
