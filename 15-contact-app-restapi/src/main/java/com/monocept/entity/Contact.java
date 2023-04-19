package com.monocept.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int contact_id;

	private String first_name;

	private String last_name;
	
	private boolean is_active;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;

	@OneToMany(mappedBy = "contact", cascade = CascadeType.ALL)
	Set<ContactDetail> contactDetails;

	public Contact() {
		super();
	}

	public Contact(int contact_id, String first_name, String last_name, boolean is_active, User user,
			Set<ContactDetail> contactDetails) {
		super();
		this.contact_id = contact_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.is_active = is_active;
		this.user = user;
		this.contactDetails = contactDetails;
	}

	public int getContact_id() {
		return contact_id;
	}

	public void setContact_id(int contact_id) {
		this.contact_id = contact_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<ContactDetail> getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(Set<ContactDetail> contactDetails) {
		this.contactDetails = contactDetails;
	}
}