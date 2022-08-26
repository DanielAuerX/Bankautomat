package tests;

import code.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    @Test
    void readJSON_ShouldReturnStringWithContent() {
        var database = new Database();

        String actual = database.readJSON("R:\\Java\\Bankautomat\\card_data.json");

        assertFalse(actual.isBlank());
    }

    @Test
    void readJSON_ShouldReturnStringContainingPin() {
        var database = new Database();

        String actual = database.readJSON("R:\\Java\\Bankautomat\\card_data.json");

        assertTrue(actual.contains("pin"));
    }

    @Test
    void getCards_ShouldReturnArrayWithElements() {
        var database = new Database();

        ArrayList<Card> actual = database.getCards();

        assertFalse(actual.isEmpty());
    }

    @Test
    void getCards_ShouldReturnArrayWithCards() {
        var database = new Database();
        var testCard = new Card(1, 1,1,1,false);

        ArrayList<Card> actual = database.getCards();

        assertEquals(actual.get(0).getClass(), testCard.getClass());
    }

    @Test
    void getAccounts_ShouldReturnArrayWithElements() {
        var database = new Database();

        ArrayList<Account> actual = database.getAccounts();

        assertFalse(actual.isEmpty());
    }

    @Test
    void getAccounts_ShouldReturnArrayWithCards() {
        var database = new Database();
        var testAccount = new Account(1, new int[] {1}, new int[] {1}, 1);

        ArrayList<Account> actual = database.getAccounts();

        assertEquals(actual.get(0).getClass(), testAccount.getClass());
    }

    @Test
    void getCustomers_ShouldReturnArrayWithElements() {
        var database = new Database();

        ArrayList<Customer> actual = database.getCustomers();

        assertFalse(actual.isEmpty());
    }

    @Test
    void getCustomers_ShouldReturnArrayWithCustomers() {
        var database = new Database();
        var address = new Address("a", "1a", 123, "a");
        var testCustomer = new Customer("a", "a", 0, address, "1", "a", 1);

        ArrayList<Customer> actual = database.getCustomers();

        assertEquals(actual.get(0).getClass(), testCustomer.getClass());
    }

    @Test
    void writeAccountData_Deposit1ShouldWriteOldBalancePlus1() {
        var database = new Database();
        var eos = new EosBankingApplication();
        var account = eos.getAccount(11111);
        double oldBalance = account.getBalance();
        account.deposit(1);

        database.writeAccountData(account);

        var accountNew = eos.getAccount(11111);
        assertEquals(oldBalance+1, accountNew.getBalance());

        accountNew.withdraw(1);
        database.writeAccountData(accountNew);
    }

    @Test
    void writeAccountData_NoChangeShouldWriteOldBalance() {
        var database = new Database();
        var eos = new EosBankingApplication();
        var account = eos.getAccount(11111);
        double oldBalance = account.getBalance();

        database.writeAccountData(account);

        var accountNew = eos.getAccount(11111);
        assertEquals(oldBalance, accountNew.getBalance());
    }

    @Test
    @Disabled("Blocks card 1234, isBlocked has to be set false manually")
    void writeCardData() {
        //card 1234 should not be blocked
        var database = new Database();
        var eos = new EosBankingApplication();
        var card = eos.getCard(1234);
        card.blockCard(true);

        database.writeCardData(card);

        var cardNew = eos.getCard(1234);
        assertTrue(cardNew.getIsBlocked());
    }
}