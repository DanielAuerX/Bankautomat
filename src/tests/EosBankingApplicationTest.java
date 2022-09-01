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

        assertThrows(NumberFormatException.class, eos::checkCardID); //    ()->{eos.checkCardID();}
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
    void validatePin_correctPinShouldReturnTrue(){
        var eos = new EosBankingApplication();
        var repository = new Repository();
        var card = repository.getCard(1236);
        String userInput = "333";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        boolean actual = eos.validatePin(card);

        assertTrue(actual);

    }

    @Test
    @Disabled ("Second input is not working")
    void validatePin_threeWrongPinsShouldReturnFalse(){
        var eos = new EosBankingApplication();
        var repository = new Repository();
        var card = repository.getCard(1236);
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