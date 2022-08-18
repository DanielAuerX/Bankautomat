package tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class EosBankingApplicationTest {

    @Test
    @Disabled("Is private")
    void askForAmount() {
        String userInput = "-10";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        //EosBankingApplication.askForAmount();




    }
    /*String userInput = "hallo";
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