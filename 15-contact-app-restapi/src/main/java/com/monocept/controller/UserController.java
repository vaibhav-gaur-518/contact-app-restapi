package com.monocept.controller;

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

import com.monocept.entity.User;
import com.monocept.repository.UserRepository;

@RestController
@RequestMapping("/admin/users")
public class UserController {
   @Autowired
   private UserRepository userRepository;
   
   @GetMapping("")
   public List<User> getUsers() {
      return userRepository.findAll();
   }
   
   @PostMapping("")
   public User createUser(@RequestBody User user) {
      return userRepository.save(user);
   }
   
   @GetMapping("/{id}")
   public Optional<User> getUserById(@PathVariable("id") int id) {
      return userRepository.findById(id);
   }
   
   @PutMapping("/{id}")
   public User updateUser(@PathVariable("id") int id, @RequestBody User userRequest) {
      Optional<User> user = userRepository.findById(id);
      
      user.get().setFirst_name(userRequest.getFirst_name());
      user.get().setLast_name(userRequest.getLast_name());
      user.get().setIs_admin(userRequest.getIs_admin());
      
      return userRepository.save(user.get());
   }
   
   @DeleteMapping("/{id}")
   public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
      Optional<User> user = userRepository.findById(id);
      
      userRepository.delete(user.get());
      
      return ResponseEntity.ok().build();
   }
}
