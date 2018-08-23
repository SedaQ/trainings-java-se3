package com.trainings.facade.api.dto.contact;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.trainings.facade.api.dto.user.ContactUserDTO;

/**
 * 
 * @author Pavel Šeda
 *
 */
@JacksonXmlRootElement(localName="contact")
public class ContactDTO {

	private Long idContact;
	private String contact;
	private ContactUserDTO contactUserDTO;

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

	public ContactUserDTO getContactUserDTO() {
		return contactUserDTO;
	}

	public void setContactUserDTO(ContactUserDTO contactUserDTO) {
		this.contactUserDTO = contactUserDTO;
	}

}
