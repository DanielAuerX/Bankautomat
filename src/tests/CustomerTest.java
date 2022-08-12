package tests;

import code.Customer;
import junit.framework.TestCase;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class CustomerTest extends TestCase {

    public void testGreeting_ShouldReturnHerrMeier() {
        Customer herrMeier = new Customer("Hans", "Meier", 0, 1001);
        assertEquals("Herr Meier", herrMeier.greeting());
    }

    public void testGreeting_ShouldReturnFrauMeier(){
        Customer frauMeier = new Customer("Hansi", "Meier", 1, 1001);
        assertEquals("Frau Meier", frauMeier.greeting());
    }

    public void testGreeting_ShouldReturnFirstAndLastName1(){
        Customer xxxMeier = new Customer("Hans", "Meier", 2, 1001);
        assertEquals("Hans Meier", xxxMeier.greeting());
    }

    public void testGreeting_ShouldReturnFirstAndLastName2(){
        Customer xxxMaffay = new Customer("Peter", "Maffay", 3, 1001);
        assertEquals("Peter Maffay", xxxMaffay.greeting());
    }

    public void testGreeting_ShouldReturnFirstAndLastName3(){
        Customer xxxMafia = new Customer("Peter", "Mafia", -1, 1001);
        assertEquals("Peter Mafia", xxxMafia.greeting());
    }

    public void testValidateCustomerNum_CorrectNumShouldReturnTrue() {
        String userInput = "1001";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);
        boolean actual = Customer.validateCustomerNum(new Scanner(System.in));


        assertTrue(actual);
    }

    public void testValidateCustomerNum_ShouldReturnTrueAfterSecondTry() {
        String userInput = "999912313" + "\n1001";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        Scanner scanner = new Scanner(System.in);

        boolean actual = Customer.validateCustomerNum(scanner);

        assertTrue(actual);
    }

    public void testValidateCustomerNum_ShouldReturnTrueAfterThirdTry() {
        String userInput = "999912313" + "\n1240141234" + "\n1001";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        Scanner scanner = new Scanner(System.in);

        boolean actual = Customer.validateCustomerNum(scanner);

        assertTrue(actual);
    }

    public void testValidateCustomerNum_ShouldReturnTrueAfterForthTry() {
        String userInput = "999912313" + "\n1240141234" + "\n1239201" + "\n1001";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        Scanner scanner = new Scanner(System.in);

        boolean actual = Customer.validateCustomerNum(scanner);

        assertTrue(actual);
    }

    public void testValidateCustomerNum_ShouldReturnFalseAfterForthTry() {
        String userInput = "999912313" + "\n1240141234" + "\n1239201" + "\n112350";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        Scanner scanner = new Scanner(System.in);

        boolean actual = Customer.validateCustomerNum(scanner);

        assertFalse(actual);
    }

    public void testValidateCustomerNum_ShouldReturnNoLettersAllowedWithThreeTriesLeft() {
        String userInput = "moin" + "\n1001";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        String expectedCatch = "Bitte Kundennummer eingeben: Bitte ausschließlich Zahlen eingeben.";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        Scanner scanner = new Scanner(System.in);
        Customer.validateCustomerNum(scanner);

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = lines[lines.length-3];

        assertEquals(expectedCatch, actual);
    }

    public void testValidateCustomerNum_ShouldReturnThreeMoreTries() {
        String userInput = "999" + "\n1001";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        String expectedCatch = "Bitte Kundennummer eingeben: Diese Kundennummer ist nicht bekannt. Versuchen Sie es erneut.\nSie haben noch 3 Versuche.";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        Scanner scanner = new Scanner(System.in);
        Customer.validateCustomerNum(scanner);

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = lines[lines.length-2];

        assertEquals(expectedCatch, actual);
    }

    public void testValidateCustomerNum_ShouldReturnOneMoreTry() {
        String userInput = "999" + "\n999" + "\n999" + "\n1001";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        String expectedCatch = "Bitte Kundennummer eingeben: Diese Kundennummer ist nicht bekannt. Versuchen Sie es erneut.\nSie haben noch 1 Versuch.";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        Scanner scanner = new Scanner(System.in);
        Customer.validateCustomerNum(scanner);

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = lines[lines.length-2];

        assertEquals(expectedCatch, actual);
    }

    public void testValidateCustomerNum_ShouldReturnNoMoreTry() {
        String userInput = "999" + "\n999" + "\n999" + "\n999";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        String expectedCatch = "Bitte Kundennummer eingeben: "+
                "Diese Kundennummer ist nicht bekannt. \nFalls Sie Ihre Kundennummer vergessen haben, wenden Sie sich an das Bankpersonal.";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        Scanner scanner = new Scanner(System.in);
        Customer.validateCustomerNum(scanner);

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = lines[lines.length-1];

        assertEquals(expectedCatch, actual);
    }


}