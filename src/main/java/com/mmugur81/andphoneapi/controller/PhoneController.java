package com.mmugur81.andphoneapi.controller;

import com.mmugur81.andphoneapi.model.Phone;
import com.mmugur81.andphoneapi.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PhoneController {

    private final PhoneService phoneService;

    @Autowired
    public PhoneController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @RequestMapping(value = "/phone", method = RequestMethod.GET)
    public List<Phone> listAllPhones() {
        return phoneService.listAll();
    }
}
