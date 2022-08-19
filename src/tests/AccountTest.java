package tests;

import code.Account;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void deposit_ShouldReturn150() {
        ArrayList<String> customerId = new ArrayList<>();
        var account = new Account(1, customerId, 1, 100.00);

        account.deposit(50.00);

        assertEquals(150.00, account.getBalance());
    }

    @Test
    void deposit_ShouldReturn50Point25() {
        ArrayList<String> customerId = new ArrayList<>();
        var account = new Account(1, customerId, 1, 10.00);

        account.deposit(40.25);

        assertEquals(50.25, account.getBalance());
    }

    @Test
    void deposit_ShouldReturn1() {
        ArrayList<String> customerId = new ArrayList<>();
        var account = new Account(1, customerId, 1, -10);

        account.deposit(11.00);

        assertEquals(1, account.getBalance());
    }

    @Test
    void withdraw_ShouldReturn900() {
        ArrayList<String> customerId = new ArrayList<>();
        var account = new Account(1, customerId, 1, 1000.00);

        account.withdraw(100.00);

        assertEquals(900, account.getBalance());
    }

    @Test
    void withdraw_ShouldReturnMinus1000() {
        ArrayList<String> customerId = new ArrayList<>();
        var account = new Account(1, customerId, 1, 1000.00);

        account.withdraw(2000.00);

        assertEquals(-1000.00, account.getBalance());
    }

    @Test
    @Disabled("Deleted method")
    void testMethod_ShouldThrowE(){
        ArrayList<String> customerId = new ArrayList<>();
        var account = new Account(1, customerId, 1, 100.00);

        /*assertThrows(IllegalArgumentException.class,
                () -> {
                    account.testMethod(-10);
                });

         */
    }
}