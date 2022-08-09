import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void getBalance() {
        Account account = new Account(1001, 110, 100.00);
        assertEquals("Ihr Kontostand beträgt: 100,00€\n", account.getBalance());

    }

    @Test
    void deposit() {
    }

    @Test
    void withdraw() {
    }

    @Test
    void validatePin() {
    }
}