package com.mmugur81.andphoneapi.controller;

import com.mmugur81.andphoneapi.model.Phone;
import com.mmugur81.andphoneapi.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PhoneController {

    private final PhoneService phoneService;

    @Autowired
    public PhoneController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping("/phone")
    public ResponseEntity<List<Phone>> listAllPhones() {
        List<Phone> phones = phoneService.listAll();
        return new ResponseEntity<>(phones, HttpStatus.OK);
    }

    @GetMapping("/customer/{customerId}/phones")
    public ResponseEntity<List<Phone>> listCustomerPhones(@PathVariable long customerId) {
        // Normally would test for the existence of the customer
        return new ResponseEntity<>(phoneService.findByCustomerId(customerId), HttpStatus.OK);
    }

    @PostMapping("/phone/{phoneId}/activate")
    public ResponseEntity activatePhone(@PathVariable long phoneId) {
        try {
            boolean activated = phoneService.activatePhone(phoneId);
            return new ResponseEntity<>(activated? HttpStatus.NO_CONTENT : HttpStatus.NOT_MODIFIED);
        }
        catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
