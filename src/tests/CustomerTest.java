package tests;

import code.Address;
import code.Customer;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void customer_shouldInstantiateObject() throws Exception {
        var address = new Address("Hauptstraße", "1a", 13359, "Berlin");
        var birthday = new Date(10, Calendar.NOVEMBER, 2000);

        Customer customer = new Customer("Peter", "Pahn", 0, address, birthday, "PP@hotmail.com", "1");

        assertEquals("Pahn", customer.getLastName());

    }

    @Test
    void customer_invalidEmailShouldThrowException() {
        var address = new Address("Hauptstraße", "1a", 13359, "Berlin");
        var birthday = new Date(10, Calendar.NOVEMBER, 2000);


        assertThrows(Exception.class, ()->{
            new Customer("Peter",
                    "Pahn",
                    0,
                    address,
                    birthday,
                    "INVALID_EMAIL.com",
                    "1");
        });

    }
}