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