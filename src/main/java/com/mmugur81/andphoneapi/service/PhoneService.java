package com.mmugur81.andphoneapi.service;

import com.mmugur81.andphoneapi.model.Phone;

import java.util.List;

public interface PhoneService {
    List<Phone> listAll();

    List<Phone> findByCustomerId(long customerId);

    boolean activatePhone(long phoneId);
}
