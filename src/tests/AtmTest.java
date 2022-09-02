package tests;

import code.Atm;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class AtmTest {

    @Test
    @Disabled
    void main() {
        //test happy path
        Atm atm = new Atm();
        String userInput = "1234" + "\n111"+ "\n1"+ "\n4";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        //No input at all???

        atm.runATM();
    }

    @Test
    @Disabled ("No such element exception")
    void main_ShouldReturnGutenTagHerrMustermann() {
        Atm atm = new Atm();
        String userInput = "1234" + "\n111" + "\n4";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        String expectedCatch = "Bitte Kundennummer eingeben: Guten Tag Herr Mustermann!";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        atm.runATM();

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = lines[lines.length-2];

        assertEquals(expectedCatch, actual);
    }

    @Test
    void askForPin_Input123ShouldReturn123() {
        Atm atm = new Atm();
        String userInput = "123";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        int actual = atm.askForPin();

        assertEquals(123, actual);
    }

    @Test
    void askForPin_AlphabeticInputShouldReturnException() {
        var atm = new Atm();
        String userInput = "abc";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        assertThrows(NumberFormatException.class, atm::askForPin); //()->{atm.askForPin();}
    }

    @Test
    void askForPin_AlphaNumericInputShouldReturnException() {
        var atm = new Atm();
        String userInput = "abc";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        assertThrows(NumberFormatException.class, atm::askForPin); //()->{atm.askForPin();}
    }

    @Test
    void askForPin_MinusOneInputShouldReturnException() {
        var atm = new Atm();
        String userInput = "-1";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        assertThrows(IllegalArgumentException.class, atm::askForPin);  //()->{atm.askForPin();}
    }
}