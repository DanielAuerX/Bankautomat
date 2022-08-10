import junit.framework.TestCase;

import java.util.Scanner;

public class CustomerTest extends TestCase {

    public void testGreetingShouldReturnHerrMeier() {
        Customer herrMeier = new Customer("Hans", "Meier", 0, 1001);
        assertEquals("Herr Meier", herrMeier.greeting());
    }

    public void testGreetingShouldReturnFrauMeier(){
        Customer frauMeier = new Customer("Hansi", "Meier", 1, 1001);
        assertEquals("Frau Meier", frauMeier.greeting());
    }

    public void testGreetingShouldReturnFirstAndLastName1(){
        Customer xxxMeier = new Customer("Hans", "Meier", 2, 1001);
        assertEquals("Hans Meier", xxxMeier.greeting());
    }

    public void testGreetingShouldReturnFirstAndLastName2(){
        Customer xxxMaffay = new Customer("Peter", "Maffay", 3, 1001);
        assertEquals("Peter Maffay", xxxMaffay.greeting());
    }

    public void testGreetingShouldReturnFirstAndLastName3(){
        Customer xxxMafia = new Customer("Peter", "Mafia", -1, 1001);
        assertEquals("Peter Mafia", xxxMafia.greeting());
    }

    public void testvalidateCustomerNum() {
        Scanner scanner = null;
        assertFalse(Customer.validateCustomerNum(scanner));
    }
}