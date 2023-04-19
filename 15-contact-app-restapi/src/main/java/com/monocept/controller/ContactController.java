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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monocept.entity.Contact;
import com.monocept.entity.User;
import com.monocept.repository.ContactRepository;
import com.monocept.repository.UserRepository;

@RestController
@RequestMapping("/staff/contacts")
public class ContactController {
	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private UserRepository userRepository;

//	@GetMapping("")
//	public List<Contact> getContacts() {
//		List<User> user = userRepository.findAll();
//
//		return contactRepository.findAll(user);
//	}

	@PostMapping("/{id}")
	public ResponseEntity<Object> createContact(@PathVariable("id") int id, @RequestBody Contact contact) {
		Optional<User> user = userRepository.findById(id);
		
		if (user == null) {
	        return ResponseEntity.notFound().build();
	    }
		contact.setUser(user.get());
		Contact save = contactRepository.save(contact);
		user.get().getContacts().add(save);
	    return ResponseEntity.created(URI.create("/persons/" + save.getContact_id())).body(save);
	}

	@GetMapping("/{id}")
	public Optional<Contact> getContactById(@PathVariable("id") int id) {
		return contactRepository.findById(id);
	}

	@PutMapping("/{id}")
	public Contact updateContact(@PathVariable("id") int id, @RequestBody Contact contactRequest) {
		Optional<Contact> contact = contactRepository.findById(id);

		contact.get().setFirst_name(contactRequest.getFirst_name());
		contact.get().setLast_name(contactRequest.getLast_name());
		contact.get().setIs_active(contactRequest.getIs_active());

		System.out.println(contact.get().getIs_active());
		return contactRepository.save(contact.get());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteContact(@PathVariable("id") int id) {
		Optional<Contact> contact = contactRepository.findById(id);

		contactRepository.delete(contact.get());

		return ResponseEntity.ok().build();
	}

}
