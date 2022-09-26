package tests;

import code.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonIOTest {

    @Test
    void readJSON_ShouldReturnStringWithContent() {
        var json = new JsonIO();

        String actual = json.readJson("R:\\Java\\Bankautomat\\card_data.json");

        assertFalse(actual.isBlank());
    }

    @Test
    void readJSON_ShouldReturnStringContainingPin() {
        var json = new JsonIO();

        String actual = json.readJson("R:\\Java\\Bankautomat\\card_data.json");

        assertTrue(actual.contains("pin"));
    }

    @Test
    void writeAccountData_Deposit1ShouldWriteOldBalancePlus1() {
        var json = new JsonIO();
        var repository = new Repository();
        var account = repository.getAccount(11111);
        double oldBalance = account.getBalance();
        account.deposit(1);

        json.writeAccountData(account);

        var accountNew = repository.getAccount(11111);
        assertEquals(oldBalance+1, accountNew.getBalance());

        accountNew.withdraw(1);
        json.writeAccountData(accountNew);
    }

    @Test
    void writeAccountData_NoChangeShouldWriteOldBalance() {
        var json = new JsonIO();
        var repository = new Repository();
        var account = repository.getAccount(11111);
        double oldBalance = account.getBalance();

        json.writeAccountData(account);

        var accountNew = repository.getAccount(11111);
        assertEquals(oldBalance, accountNew.getBalance());
    }

    @Test
    @Disabled("Blocks card 1234, isBlocked has to be set false manually")
    void writeCardData() {
        //card 1234 should not be blocked
        var json = new JsonIO();
        var repository = new Repository();
        var card = repository.getCard(1234);
        //card.checkPin(0000);

        json.writeCardData(card);

        var cardNew = repository.getCard(1234);
        assertTrue(cardNew.isBlocked());
    }
}