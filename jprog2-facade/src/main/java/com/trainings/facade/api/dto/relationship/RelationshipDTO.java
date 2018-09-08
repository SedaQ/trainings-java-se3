package com.trainings.facade.api.dto.relationship;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * 
 * @author Pavel Šeda
 *
 */
@JacksonXmlRootElement(localName = "relationship")
public class RelationshipDTO {

	private Long idRelationship;
	private String note;

	public RelationshipDTO() {
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RelationshipDTO [idRelationship=");
		builder.append(idRelationship);
		builder.append(", note=");
		builder.append(note);
		builder.append("]");
		return builder.toString();
	}

}
