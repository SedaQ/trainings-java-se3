package com.trainings.facade.api.dto.role;

import javax.validation.constraints.NotEmpty;

/**
 * 
 * @author Pavel Šeda
 *
 */
public class RoleCreateDTO {

	@NotEmpty
	private String title;

	public RoleCreateDTO() {
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
		builder.append("RoleCreateDTO [title=");
		builder.append(title);
		builder.append("]");
		return builder.toString();
	}

}
