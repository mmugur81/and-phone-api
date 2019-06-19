package com.mmugur81.andphoneapi.service;

import com.mmugur81.andphoneapi.model.Customer;
import com.mmugur81.andphoneapi.model.Phone;
import com.mmugur81.andphoneapi.repository.PhoneRepository;
import com.mmugur81.andphoneapi.util.TestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PhoneServiceImplTest {

    @Mock
    private PhoneRepository phoneRepository;

    @InjectMocks
    private PhoneServiceImpl phoneService;

    private List<Phone> expectedPhones = new ArrayList<>();

    @Before
    public void setUp() {
        // Create a random list of phones
        expectedPhones.add(TestHelper.randomPhone(true));
        expectedPhones.add(TestHelper.randomPhone(true));
        expectedPhones.add(TestHelper.randomPhone(false));
    }

    @Test
    public void listAll() {
        when(phoneRepository.findAll()).thenReturn(expectedPhones);

        List<Phone> actualPhones = phoneService.listAll();

        assertEquals(expectedPhones, actualPhones);
    }

    @Test
    public void findByCustomerId() {
        long customerId = 1;
        when(phoneRepository.findByCustomerId(eq(customerId))).thenReturn(expectedPhones);

        List<Phone> actualPhones = phoneService.findByCustomerId(customerId);

        assertEquals(expectedPhones, actualPhones);
    }

    @Test
    public void activatePhoneSucceeds() {
        long phoneId = 1L;
        Phone expected = TestHelper.randomPhone(false);
        when(phoneRepository.getOne(eq(phoneId))).thenReturn(expected);

        boolean activated = phoneService.activatePhone(phoneId);

        assertTrue(activated);
        assertTrue(expected.isActive());
    }

    @Test
    public void activatePhoneDoesNothingIfPhoneAlreadyActive() {
        long phoneId = 1L;
        Phone expected = TestHelper.randomPhone(true);
        when(phoneRepository.getOne(eq(phoneId))).thenReturn(expected);

        boolean activated = phoneService.activatePhone(phoneId);
        assertFalse(activated);
        assertTrue(expected.isActive());
    }

    @Test(expected = EntityNotFoundException.class)
    public void activatePhoneFailsIfPhoneNotFound() {
        long phoneId = 1L;
        when(phoneRepository.getOne(eq(phoneId))).thenThrow(new EntityNotFoundException());

        boolean activated = phoneService.activatePhone(phoneId);
    }

}