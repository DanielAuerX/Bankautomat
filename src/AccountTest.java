import junit.framework.TestCase;

import java.io.*;
import java.util.Scanner;

public class AccountTest extends TestCase {

    public void testGetBalanceShouldReturnHundred() {
            Account account = new Account(1001, 110, 100.00);
            assertEquals("\n"+Main.TEXT_BACKGROUND+"Ihr Kontostand beträgt: "+Main.BOLD+"100,00€\n"+Main.RESET, account.getBalance());
    }

    public void testGetBalanceShouldReturnMinusOneInRed() {
        Account account = new Account(1001, 110, -1);
        assertEquals("\n"+Main.TEXT_BACKGROUND+"Ihr Kontostand beträgt: "+Main.BOLD+Main.RED_BACKGROUND+"-1,00€\n"+Main.RESET, account.getBalance());
    }

    public void testGetBalanceShouldReturnZero() {
        Account account = new Account(1001, 110, 0);
        assertEquals("\n"+Main.TEXT_BACKGROUND+"Ihr Kontostand beträgt: "+Main.BOLD+"0,00€\n"+Main.RESET, account.getBalance());
    }

    public void testGetBalanceShouldReturnTwoKommaZeroZero() {
        Account account = new Account(1001, 110, 1.99999);
        assertEquals("\n"+Main.TEXT_BACKGROUND+"Ihr Kontostand beträgt: "+Main.BOLD+"2,00€\n"+Main.RESET, account.getBalance());
    }

    public void testDepositShouldReturnNewBalanceHundredFifty() {
        Account testAccount = new Account(1, 1, 50.0);
        String userInput = "100";

        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        testAccount.deposit(new Scanner(System.in));

        assertEquals(String.valueOf(150.0), String.valueOf(testAccount.balance));
    }

    public void testDepositShouldReturnNewBalanceMinusOne() {
        Account testAccount = new Account(1, 1, -2.0);
        String userInput = "1";

        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        testAccount.deposit(new Scanner(System.in));

        assertEquals(String.valueOf(-1.0), String.valueOf(testAccount.balance));
    }

    public void testDepositShouldReturnNoLettersAllowed() {
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


    public void testWithdrawShouldReturnOne() {
        Account testAccount = new Account(1, 1, 100.0);
        String userInput = "99";

        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        testAccount.withdraw(new Scanner(System.in));

        assertEquals(String.valueOf(1.0), String.valueOf(testAccount.balance));
    }

    public void testWithdrawShouldReturnMinusOne() {
        Account testAccount = new Account(1, 1, 99.0);
        String userInput = "100";

        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        testAccount.withdraw(new Scanner(System.in));

        assertEquals(String.valueOf(-1.0), String.valueOf(testAccount.balance));
    }

    public void testWithdrawShouldReturnZero() {
        Account testAccount = new Account(1, 1, 1.0);
        String userInput = "1";

        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        testAccount.withdraw(new Scanner(System.in));

        assertEquals(String.valueOf(0.0), String.valueOf(testAccount.balance));
    }

    public void testWithdrawShouldReplaceKomma() {
        Account testAccount = new Account(1, 1, 3.0);
        String userInput = "1,50";

        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        testAccount.withdraw(new Scanner(System.in));

        assertEquals(String.valueOf(1.50), String.valueOf(testAccount.balance));
    }

    public void testWithdrawShouldReturnNoLettersAllowed() {
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

    public void testValidatePinCorrectPinShouldReturnTrue() {
        Account testAccount = new Account(1, 999, 1.0);
        String userInput = "999";

        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        assertTrue(testAccount.validatePin(new Scanner(System.in)));
    }

    /*public void testValidateShouldReturnNoLettersAllowed() {
        Account testAccount = new Account(1, 999, 1.0);
        String userInput = "abc";

        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        String expectedCatch = "Bitte ausschließlich Zahlen eingeben.";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        testAccount.validatePin(new Scanner(System.in));

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = lines[lines.length-1];

        assertEquals(expectedCatch, actual);
    }*/

   /* public void testValidatePinWrongPinOnce() {
        Account testAccount = new Account(1, 987, 1.0);
        String userInput = String.format("111%123%987",
                System.lineSeparator(),
                System.lineSeparator());

        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        assertTrue(testAccount.validatePin(new Scanner(System.in)));
    }*/
}