package tests;

import code.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EosBankingApplicationTest {

    @Test
    void checkCardID_CorrectIdShouldReturnTrue(){
        var eos = new EosBankingApplication();
        String userInput = "1234";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        boolean actual = eos.checkCardID();

        assertTrue(actual);
    }

    @Test
    void checkCardID_AlphaNumericInputShouldThrowException(){
        var eos = new EosBankingApplication();
        String userInput = "abc";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        assertThrows(NumberFormatException.class, ()->{
            eos.checkCardID();});
    }

    @Test
    void checkCardID_oneWrongOneCorrectInputShouldReturnTrue(){
        var eos = new EosBankingApplication();
        String userInput = "999" + "\n1234";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        boolean actual = eos.checkCardID();

        assertTrue(actual);
    }

    @Test
    void checkCardID_oneWrongInputShouldPrintThreeMoreTries(){
        var eos = new EosBankingApplication();
        String userInput = "999" + "\n1234";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        String expectedCatch = "Bitte Kartennummer eingeben: Diese Kartennummer ist nicht bekannt. Versuchen Sie es erneut.\nSie haben noch 3 Versuche.";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        eos.checkCardID();

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = lines[lines.length-2];

        assertEquals(expectedCatch, actual);
    }

    @Test
    void checkCardID_fourWrongInputsShouldReturnFalse(){
        var eos = new EosBankingApplication();
        String userInput = "1" + "\n1"+ "\n1"+ "\n1";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        boolean actual = eos.checkCardID();

        assertFalse(actual);
    }

    @Test
    void instantiateCustomer_correctIDShouldReturnCustomerClass(){
        var eos = new EosBankingApplication();
        var address = new Address("1", 1, 1, "1");
        var testCustomer = new Customer("1", "1", 1, address, "1", "1", 1);

        var customer = eos.instantiateCustomer(3333);

        assertEquals(testCustomer.getClass(), customer.getClass());
    }

    @Test
    void instantiateCustomer_1111ShouldReturnCustomerWurst(){
        var eos = new EosBankingApplication();

        var customer = eos.instantiateCustomer(1111);

        assertEquals("Wurst", customer.lastName());
    }

    @Test
    void instantiateCustomer_3333ShouldReturnCustomerEike(){
        var eos = new EosBankingApplication();

        var customer = eos.instantiateCustomer(3333);

        assertEquals("Eike", customer.firstName());
    }

    @Test
    void instantiateCustomer_WrongCustomerIDShouldThrowException(){
        //IOOBE: index starts at -1 so that the first customer data is not being returned
        var eos = new EosBankingApplication();

        assertThrows(IndexOutOfBoundsException.class, ()->{
            eos.instantiateCustomer(0000);;});
    }

    @Test
    void instantiateAccount_ShouldReturn11111(){
        var eos = new EosBankingApplication();

        var account = eos.instantiateAccount(11111, 1236);

        assertEquals(11111, account.getId());
    }

    @Test
    void instantiateAccount_ShouldReturnTypeAccount(){
        var eos = new EosBankingApplication();
        ArrayList <String> customerID= new ArrayList<>();
        var testAccount = new Account(1,customerID, 1, 1.00);

        var account = eos.instantiateAccount(11111, 1236);

        assertEquals(testAccount.getClass(), account.getClass());
    }

    @Test
    void instantiateAccount_ShouldInstantiateAccount11112WithCard1234(){
        //two cards for one account
        var eos = new EosBankingApplication();

        var account = eos.instantiateAccount(11112, 1234);

        assertEquals(11112, account.getId());
    }

    @Test
    void instantiateAccount_ShouldInstantiateAccount11112WithCard1235(){
        //two cards for one account
        var eos = new EosBankingApplication();

        var account = eos.instantiateAccount(11112, 1235);

        assertEquals(11112, account.getId());
    }

    @Test
    void instantiateCard_ShouldReturnTypeCard (){
        var eos = new EosBankingApplication();
        Card testCard = new Card(1, 1, 1, 1, false);

        var card = eos.instantiateCard("1236");

        assertEquals(testCard.getClass(), card.getClass());
    }

    @Test
    void instantiateCard_Card1236ShouldReturn11111(){
        var eos = new EosBankingApplication();

        var card = eos.instantiateCard("1236");

        assertEquals(11111, card.getAccountID());
    }

    @Test
    void instantiateCard_Card1234ShouldReturn11112(){
        //two cards should be able to access the same account
        var eos = new EosBankingApplication();

        var card = eos.instantiateCard("1234");

        assertEquals(11112, card.getAccountID());
    }

    @Test
    void instantiateCard_Card1235ShouldReturn11112(){
        //two cards should be able to access the same account
        var eos = new EosBankingApplication();

        var card = eos.instantiateCard("1235");

        assertEquals(11112, card.getAccountID());
    }

    @Test
    void validatePin_correctPinShouldReturnTrue(){
        var eos = new EosBankingApplication();
        var card = eos.instantiateCard("1236");
        String userInput = "333";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        boolean actual = eos.validatePin(card);

        assertTrue(actual);

    }

    @Test
    @Disabled ("Second input is not working")
    void validatePin_threeWrongPinsShouldReturnFalse(){
        var eos = new EosBankingApplication();
        var card = eos.instantiateCard("1236");
        String userInput = "1"+"\n333"+"\n1";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        boolean actual = eos.validatePin(card);

        assertFalse(actual);

    }








    /*           String userInput = "999" + "\n1001";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        String expectedCatch = "Bitte Kundennummer eingeben: Diese Kundennummer ist nicht bekannt. Versuchen Sie es erneut.\nSie haben noch 3 Versuche.";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        Scanner scanner = new Scanner(System.in);
        Customer.validateCustomerID(scanner);

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = lines[lines.length-2];

        assertEquals(expectedCatch, actual);




        String userInput = "hallo";

        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);
        String expectedCatch = "Bitte einen Betrag aus Zahlen eingeben.\nBuchstaben sind nicht gestattet!";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        testAccount.deposit(new Scanner(System.in));

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = lines[lines.length-1];

        assertEquals(expectedCatch, actual);*/
}