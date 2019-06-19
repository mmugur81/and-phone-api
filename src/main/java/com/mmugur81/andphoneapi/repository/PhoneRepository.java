package com.mmugur81.andphoneapi.repository;

import com.mmugur81.andphoneapi.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long>, JpaSpecificationExecutor {

    List<Phone> findByCustomerId(long customerId);
}
