package com.trainings.facade.api.dto.contact;

/**
 * 
 * @author Pavel Šeda
 *
 */
public class ContactTypeDTO {

	private Long idContactType;
	private String title;

	public ContactTypeDTO() {
	}

	public Long getIdContactType() {
		return idContactType;
	}

	public void setIdContactType(Long idContactType) {
		this.idContactType = idContactType;
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
		builder.append("ContactTypeDTO [idContactType=");
		builder.append(idContactType);
		builder.append(", title=");
		builder.append(title);
		builder.append("]");
		return builder.toString();
	}

}
