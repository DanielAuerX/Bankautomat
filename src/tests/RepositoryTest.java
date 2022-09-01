package tests;

import code.*;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {

    @Test
    void getCustomer_correctIDShouldReturnCustomerClass() throws ParseException {
        var repository = new Repository();
        var address = new Address("1", "1", 1, "1");
        var birthday = new Date(2000, Calendar.FEBRUARY, 12);
        var testCustomer = new Customer("1", "1", 1, address, birthday, "1", 1);

        var customer = repository.getCustomer(3333);

        assertEquals(testCustomer.getClass(), customer.getClass());
    }

    @Test
    void getCustomer_1111ShouldReturnCustomerWurst(){
        var repository = new Repository();

        var customer = repository.getCustomer(1111);

        assertEquals("Wurst", customer.getLastName());
    }

    @Test
    void getCustomer_3333ShouldReturnCustomerEike(){
        var repository = new Repository();

        var customer = repository.getCustomer(3333);

        assertEquals("Eike", customer.getFirstName());
    }

    @Test
    void getCustomer_WrongCustomerIDShouldThrowException(){
        //IOOBE: index starts at -1 so that the first customer data is not being returned
        var repository = new Repository();

        assertThrows(IndexOutOfBoundsException.class, ()->{
            repository.getCustomer(0000);;});
    }

    @Test
    void getAccount_ShouldReturn11111(){
        var repository = new Repository();

        var account = repository.getAccount(11111);

        assertEquals(11111, account.getId());
    }

    @Test
    void getAccount_ShouldReturnTypeAccount(){
        var repository = new Repository();
        var testAccount = new Account(1, new int[]{1}, new int[]{1}, 1.00);

        var account = repository.getAccount(11111);

        assertEquals(testAccount.getClass(), account.getClass());
    }

    @Test
    void getAccount_ShouldInstantiateAccount11112WithCard1234(){
        //two cards for one account
        var repository = new Repository();

        var account = repository.getAccount(11112);

        assertEquals(11112, account.getId());
    }

    @Test
    void getAccount_ShouldInstantiateAccount11112WithCard1235(){
        //two cards for one account
        var repository = new Repository();

        var account = repository.getAccount(11112);

        assertEquals(11112, account.getId());
    }

    @Test
    void getCard_ShouldReturnTypeCard (){
        var repository = new Repository();
        Card testCard = new Card(1, 1, 1, 1, false,0);

        var card = repository.getCard(1236);

        assertEquals(testCard.getClass(), card.getClass());
    }

    @Test
    void getCard_Card1236ShouldReturn11111(){
        var repository = new Repository();

        var card = repository.getCard(1236);

        assertEquals(11111, card.getAccountID());
    }

    @Test
    void getCard_Card1234ShouldReturn0(){
        var repository = new Repository();

        var card = repository.getCard(1234);

        assertEquals(0, card.getPinTries());
    }

    @Test
    void getCard_Card1234ShouldReturn11112(){
        //two cards should be able to access the same account
        var repository = new Repository();

        var card = repository.getCard(1234);

        assertEquals(11112, card.getAccountID());
    }

    @Test
    void getCard_Card1235ShouldReturn11112(){
        //two cards should be able to access the same account
        var repository = new Repository();

        var card = repository.getCard(1235);

        assertEquals(11112, card.getAccountID());
    }

    @Test
    void findCardByID_existingCardIdShouldReturnTrue() {
        var repository = new Repository();

        boolean actual = repository.findCardByID("1234");

        assertTrue(actual);
    }

    @Test
    void findCardByID_nonExistingCardIdShouldReturnFalse() {
        var repository = new Repository();

        boolean actual = repository.findCardByID("000000000");

        assertFalse(actual);
    }

    @Test
    void findCardByID_alphaNumericInputShouldThrowException() {
        var repository = new Repository();

        assertThrows(NumberFormatException.class, () -> {
            repository.findCardByID("123abc");
        });
    }



        /*
    @Test
    @Disabled ("private")
    void getCards_ShouldReturnArrayWithElements() {
        var repository = new Repository();

        ArrayList<Card> actual = repository.getCards();

        assertFalse(actual.isEmpty());
    }

    @Test
    @Disabled ("private")
    void getCards_ShouldReturnArrayWithCards() {
        var repository = new Repository();
        var testCard = new Card(1, 1,1,1,false);

        ArrayList<Card> actual = repository.getCards();

        assertEquals(actual.get(0).getClass(), testCard.getClass());
    }

    @Test
    @Disabled ("private")
    void getAccounts_ShouldReturnArrayWithElements() {
        var repository = new Repository();

        ArrayList<Account> actual = repository.getAccounts();

        assertFalse(actual.isEmpty());
    }

    @Test
    @Disabled ("private")
    void getAccounts_ShouldReturnArrayWithCards() {
        var eos = new EosBankingApplication();
        var testAccount = new Account(1, new int[] {1}, new int[] {1}, 1);

        ArrayList<Account> actual = eos.getAccounts();

        assertEquals(actual.get(0).getClass(), testAccount.getClass());
    }

    @Test
    @Disabled ("private")
    void getCustomers_ShouldReturnArrayWithElements() {
        var eos = new EosBankingApplication();

        ArrayList<Customer> actual = eos.getCustomers();

        assertFalse(actual.isEmpty());
    }

    @Test
    @Disabled ("private")
    void getCustomers_ShouldReturnArrayWithCustomers() {
        var eos = new EosBankingApplication();
        var address = new Address("a", "1a", 123, "a");
        var testCustomer = new Customer("a", "a", 0, address, "1", "a", 1);

        ArrayList<Customer> actual = eos.getCustomers();

        assertEquals(actual.get(0).getClass(), testCustomer.getClass());
    }

     */

}