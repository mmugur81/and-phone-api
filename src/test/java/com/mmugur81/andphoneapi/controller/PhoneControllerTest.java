package com.mmugur81.andphoneapi.controller;

import com.mmugur81.andphoneapi.model.Phone;
import com.mmugur81.andphoneapi.service.PhoneService;
import com.mmugur81.andphoneapi.util.TestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
 * This will test only controller behaviour and not the api endpoints
 */
@RunWith(MockitoJUnitRunner.class)
public class PhoneControllerTest {

    @Mock
    private PhoneService phoneService;

    @InjectMocks
    private PhoneController phoneController;

    private List<Phone> expectedPhones = new ArrayList<>();

    @Before
    public void setUp() {
        // Create a random list of phones
        expectedPhones.add(TestHelper.randomPhone(true));
        expectedPhones.add(TestHelper.randomPhone(true));
        expectedPhones.add(TestHelper.randomPhone(false));
    }

    @Test
    public void listAllPhones() {
        when(phoneService.listAll()).thenReturn(expectedPhones);

        ResponseEntity<List<Phone>> response = phoneController.listAllPhones();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPhones, response.getBody());
    }

    @Test
    public void listCustomerPhones() {
        long customerId = 1;
        when(phoneService.findByCustomerId(eq(customerId))).thenReturn(expectedPhones);

        ResponseEntity<List<Phone>> response = phoneController.listCustomerPhones(customerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPhones, response.getBody());
    }

    @Test
    public void activatePhoneSucceeds() {
        long phoneId = 1;
        when(phoneService.activatePhone(eq(phoneId))).thenReturn(true);

        ResponseEntity response = phoneController.activatePhone(phoneId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void activatePhoneFailsIfPhoneNotFound() {
        long phoneId = 1;
        EntityNotFoundException notFoundException = new EntityNotFoundException("Entity not found");
        when(phoneService.activatePhone(eq(phoneId))).thenThrow(notFoundException);

        ResponseEntity response = phoneController.activatePhone(phoneId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(notFoundException.getMessage(), response.getBody());
    }

    @Test
    public void activatePhoneFailDosNothingIfPhoneAlreadyActive() {
        long phoneId = 1;
        when(phoneService.activatePhone(eq(phoneId))).thenReturn(false);

        ResponseEntity response = phoneController.activatePhone(phoneId);

        assertEquals(HttpStatus.NOT_MODIFIED, response.getStatusCode());
        assertNull(response.getBody());
    }
}