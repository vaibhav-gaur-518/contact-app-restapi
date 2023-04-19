package com.monocept.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ContactDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int contact_details_id;
	
	private String type;
	
	@ManyToOne
    @JoinColumn(name = "contact_id")
    @JsonIgnore
    private Contact contact;

	public ContactDetail() {
		super();
	}

	public ContactDetail(int contact_details_id, String type, Contact contact) {
		super();
		this.contact_details_id = contact_details_id;
		this.type = type;
		this.contact = contact;
	}

	public int getContactDetailsId() {
		return contact_details_id;
	}

	public void setContactDetailsId(int contact_details_id) {
		this.contact_details_id = contact_details_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
}
