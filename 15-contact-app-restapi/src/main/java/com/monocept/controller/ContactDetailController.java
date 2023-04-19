package com.monocept.controller;

import java.net.URI;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monocept.entity.Contact;
import com.monocept.entity.ContactDetail;
import com.monocept.repository.ContactDetailRepository;
import com.monocept.repository.ContactRepository;

@RestController
@RequestMapping("/staff/contact/details")
public class ContactDetailController {
	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private ContactDetailRepository contactDetailRepository;

	@PostMapping("/{id}")
	public ResponseEntity<Object> createContactDetail(@PathVariable("id") int id, @RequestBody ContactDetail contactdetails) {
		Optional<Contact> contact = contactRepository.findById(id);
		
		if (contact == null) {
	        return ResponseEntity.notFound().build();
	    }
		contactdetails.setContact(contact.get());
		ContactDetail save = contactDetailRepository.save(contactdetails);
		contact.get().getContactDetails().add(save);
		
	    return ResponseEntity.created(URI.create("/persons/" + save.getContactDetailsId())).body(save);
	}

	@GetMapping("/{id}")
	public Optional<ContactDetail> getContactDetailById(@PathVariable("id") int id) {
		System.out.println(id);
		return contactDetailRepository.findById(id);
	}

	@PutMapping("/{id}")
	public ContactDetail updateContactDetail(@PathVariable("id") int id, @RequestBody ContactDetail contactRequest) {
		Optional<ContactDetail> contact = contactDetailRepository.findById(id);
		contact.get().setType(contactRequest.getType());
		return contactDetailRepository.save(contact.get());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteContactDetail(@PathVariable("id") int id) {
		Optional<ContactDetail> contact = contactDetailRepository.findById(id);
		contactDetailRepository.delete(contact.get());
		return ResponseEntity.ok().build();
	}

}
