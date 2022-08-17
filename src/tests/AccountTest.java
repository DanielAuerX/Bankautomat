package tests;

import code.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void getBalance() {
    }

    @Test
    void getId() {
    }

    @Test
    void deposit_ShouldReturn150() {
        Account account = new Account(1, 1, 100.00);

        account.deposit(50.00);

        assertEquals(150.00, account.getBalance());
    }

    @Test
    void deposit_ShouldReturn50Point25() {
        Account account = new Account(1, 1, 10.00);

        account.deposit(40.25);

        assertEquals(50.25, account.getBalance());
    }

    @Test
    void withdraw() {
    }
}