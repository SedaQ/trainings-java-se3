package com.trainings.facade.api.dto.relationship;

/**
 * 
 * @author Pavel Šeda
 *
 */
public class RelationshipTypeDTO {

	private Long idRelationshipType;
	private String title;

	public RelationshipTypeDTO() {
	}

	public Long getIdRelationshipType() {
		return idRelationshipType;
	}

	public void setIdRelationshipType(Long idRelationshipType) {
		this.idRelationshipType = idRelationshipType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
