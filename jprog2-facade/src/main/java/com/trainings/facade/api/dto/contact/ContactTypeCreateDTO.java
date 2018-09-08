package com.trainings.facade.api.dto.contact;

/**
 * 
 * @author Pavel Šeda
 *
 */
public class ContactTypeCreateDTO {

	private String title;

	public ContactTypeCreateDTO() {
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
		builder.append("ContactTypeCreateDTO [title=");
		builder.append(title);
		builder.append("]");
		return builder.toString();
	}

}
