package com.mmugur81.andphoneapi.repository;

import com.mmugur81.andphoneapi.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
