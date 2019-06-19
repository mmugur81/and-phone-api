package com.mmugur81.andphoneapi.util;

import com.mmugur81.andphoneapi.model.Customer;
import com.mmugur81.andphoneapi.model.Phone;

import java.util.Random;

public class TestHelper {

    public static Phone randomPhone(boolean activated) {
        Phone phone = new Phone();

        Random random = new Random();
        phone.setId(random.nextInt(1000));
        phone.setActive(activated);
        phone.setNumber(Long.toString(random.nextLong()));
        phone.setCustomer(new Customer());

        return phone;
    }

}
