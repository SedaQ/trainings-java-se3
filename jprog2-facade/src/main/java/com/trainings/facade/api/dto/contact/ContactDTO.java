package com.trainings.facade.api.dto.contact;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.trainings.facade.api.dto.person.ContactPersonDTO;

/**
 * 
 * @author Pavel Šeda
 *
 */
@JacksonXmlRootElement(localName = "contact")
public class ContactDTO {

	private Long idContact;
	private String contact;
	private ContactPersonDTO contactPersonDTO;

	public ContactDTO() {
	}

	public Long getIdContact() {
		return idContact;
	}

	public void setIdContact(Long idContact) {
		this.idContact = idContact;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public ContactPersonDTO getContactPersonDTO() {
		return contactPersonDTO;
	}

	public void setContactUserDTO(ContactPersonDTO contactPersonDTO) {
		this.contactPersonDTO = contactPersonDTO;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ContactDTO [idContact=");
		builder.append(idContact);
		builder.append(", contact=");
		builder.append(contact);
		builder.append(", contactPersonDTO=");
		builder.append(contactPersonDTO);
		builder.append("]");
		return builder.toString();
	}

}
