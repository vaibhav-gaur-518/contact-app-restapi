package com.monocept.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.monocept.entity.User;

@RequestMapping
public interface UserRepository extends JpaRepository<User, Integer> {

	void save(Optional<User> user);}
