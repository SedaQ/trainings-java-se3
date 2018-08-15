package com.trainings.facade.api.dto.relationship;

import javax.validation.constraints.NotEmpty;

/**
 * 
 * @author Pavel Šeda
 *
 */
public class RelationshipTypeCreateDTO {

	@NotEmpty
	private String title;

	public RelationshipTypeCreateDTO() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RelationshipTypeCreateDTO [title=");
		builder.append(title);
		builder.append("]");
		return builder.toString();
	}

}
