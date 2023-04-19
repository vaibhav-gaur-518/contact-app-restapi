package com.monocept.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.monocept.entity.ContactDetail;

public interface ContactDetailRepository extends JpaRepository<ContactDetail, Integer> {}