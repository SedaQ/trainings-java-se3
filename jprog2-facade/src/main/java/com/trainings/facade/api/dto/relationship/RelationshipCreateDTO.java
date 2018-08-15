package com.trainings.facade.api.dto.relationship;

import javax.validation.constraints.NotNull;

import com.trainings.facade.api.dto.user.UserDTO;

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
	private UserDTO user1;
	@NotNull
	private UserDTO user2;

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

	public UserDTO getUser1() {
		return user1;
	}

	public void setUser1(UserDTO user1) {
		this.user1 = user1;
	}

	public UserDTO getUser2() {
		return user2;
	}

	public void setUser2(UserDTO user2) {
		this.user2 = user2;
	}

}
