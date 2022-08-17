package tests;

import code.*;
import junit.framework.TestCase;

import java.io.*;
import java.util.Scanner;

public class AccountTest extends TestCase {
    /*

    public void testGetBalance_ShouldReturnHundred() {
            Account account = new Account(1001, 110, 100.00);
            assertEquals("\n"+ EosBankingApplication.TEXT_BACKGROUND+"Ihr Kontostand beträgt: "+ EosBankingApplication.BOLD+"100,00€\n"+ EosBankingApplication.RESET, account.getBalance());
    }

    public void testGetBalance_ShouldReturnMinusOneInRed() {
        Account account = new Account(1001, 110, -1);
        assertEquals("\n"+ EosBankingApplication.TEXT_BACKGROUND+"Ihr Kontostand beträgt: "+ EosBankingApplication.BOLD+ EosBankingApplication.RED_BACKGROUND+"-1,00€\n"+ EosBankingApplication.RESET, account.getBalance());
    }

    public void testGetBalance_ShouldReturnZero() {
        Account account = new Account(1001, 110, 0);
        assertEquals("\n"+ EosBankingApplication.TEXT_BACKGROUND+"Ihr Kontostand beträgt: "+ EosBankingApplication.BOLD+"0,00€\n"+ EosBankingApplication.RESET, account.getBalance());
    }

    public void testGetBalance_ShouldReturnTwoKommaZeroZero() {
        Account account = new Account(1001, 110, 1.99999);
        assertEquals("\n"+ EosBankingApplication.TEXT_BACKGROUND+"Ihr Kontostand beträgt: "+ EosBankingApplication.BOLD+"2,00€\n"+ EosBankingApplication.RESET, account.getBalance());
    }

    public void testDeposit_ShouldReturnNewBalanceHundredFifty() {
        Account testAccount = new Account(1, 1, 50.0);
        String userInput = "100";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        testAccount.deposit(new Scanner(System.in));

        assertEquals(String.valueOf(150.0), String.valueOf(testAccount.getBalance()));
    }

    public void testDeposit_ShouldReturnNewBalanceMinusOne() {
        Account testAccount = new Account(1, 1, -2.0);
        String userInput = "1";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        testAccount.deposit(new Scanner(System.in));

        assertEquals(String.valueOf(-1.0), String.valueOf(testAccount.getBareBalance()));
    }

    public void testDeposit_ShouldReturnNoLettersAllowed() {
        Account testAccount = new Account(1, 1, 10.0);
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

        assertEquals(expectedCatch, actual);
    }


    public void testWithdraw_ShouldReturnOne() {
        Account testAccount = new Account(1, 1, 100.0);
        String userInput = "99";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        testAccount.withdraw(new Scanner(System.in));

        assertEquals(String.valueOf(1.0), String.valueOf(testAccount.getBareBalance()));
    }

    public void testWithdraw_ShouldReturnMinusOne() {
        Account testAccount = new Account(1, 1, 99.0);
        String userInput = "100";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        testAccount.withdraw(new Scanner(System.in));

        assertEquals(String.valueOf(-1.0), String.valueOf(testAccount.getBareBalance()));
    }

    public void testWithdraw_ShouldReturnZero() {
        Account testAccount = new Account(1, 1, 1.0);
        String userInput = "1";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        testAccount.withdraw(new Scanner(System.in));

        assertEquals(String.valueOf(0.0), String.valueOf(testAccount.getBareBalance()));
    }

    public void testWithdraw_ShouldReplaceKomma() {
        Account testAccount = new Account(1, 1, 3.0);
        String userInput = "1,50";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        testAccount.withdraw(new Scanner(System.in));

        assertEquals(String.valueOf(1.50), String.valueOf(testAccount.getBareBalance()));
    }

    public void testWithdraw_ShouldReturnNoLettersAllowed() {
        Account testAccount = new Account(1, 1, 100.0);
        String userInput = "moin";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);
        String expectedCatch = "Bitte einen Betrag aus Zahlen eingeben.\nBuchstaben sind nicht gestattet!";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        testAccount.withdraw(new Scanner(System.in));

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = lines[lines.length-1];

        assertEquals(expectedCatch, actual);
    }

    public void testValidatePin_CorrectPinShouldReturnTrue() {
        Account testAccount = new Account(1, 999, 1.0);
        String userInput = "999";

        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        assertTrue(testAccount.validatePin(new Scanner(System.in)));
    }

    public void testValidatePin_CorrectPinShouldReturnTrueAfterSecondTry() {
        Account testAccount = new Account(1, 999, 1.0);
        String userInput = "111"+"\n999";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        boolean actual = testAccount.validatePin(new Scanner(System.in));

        assertTrue(actual);
    }

    public void testValidatePin_CorrectPinShouldReturnTrueAfterThirdTry() {
        Account testAccount = new Account(1, 999, 1.0);
        String userInput = "111"+"\n112"+"\n999";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        boolean actual = testAccount.validatePin(new Scanner(System.in));

        assertTrue(actual);
    }

    public void testValidatePin_CorrectPinShouldReturnFalseAfterThirdTry() {
        Account testAccount = new Account(1, 999, 1.0);
        String userInput = "111"+"\n112"+"\n123";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        boolean actual = testAccount.validatePin(new Scanner(System.in));

        assertFalse(actual);
    }

    public void testValidatePin_ShouldReturnNoLettersAllowed() {
        Account testAccount = new Account(1, 999, 1.0);
        String userInput = "abc"+"\n999";

        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        String expectedCatch = "Bitte Geheimzahl eingeben: Bitte ausschließlich Zahlen eingeben.";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        testAccount.validatePin(new Scanner(System.in));

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = lines[lines.length-3];

        assertEquals(expectedCatch, actual);
    }

     */
}