package com.mmugur81.andphoneapi.service;

import com.mmugur81.andphoneapi.model.Phone;
import com.mmugur81.andphoneapi.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepository repository;

    @Autowired
    public PhoneServiceImpl(PhoneRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Phone> listAll() {
        return repository.findAll();
    }
}
