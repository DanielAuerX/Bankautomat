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
        String userInput = "1234" + "\n111"+ "\n1"+ "\n4";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        //No input at all???

        Atm.main(null);
    }

    @Test
    @Disabled ("No such element exception")
    void main_ShouldReturnGutenTagHerrMustermann() {
        String userInput = "1234" + "\n111" + "\n4";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        String expectedCatch = "Bitte Kundennummer eingeben: Guten Tag Herr Mustermann!";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        Atm.main(null);

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = lines[lines.length-2];

        assertEquals(expectedCatch, actual);
    }

    @Test
    void askForPin_Input123ShouldReturn123() {
        String userInput = "123";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        int actual = Atm.askForPin();

        assertEquals(123, actual);
    }

    @Test
    void askForPin_AlphabeticInputShouldReturnException() {
        String userInput = "abc";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        assertThrows(NumberFormatException.class, ()->{Atm.askForPin();});
    }

    @Test
    void askForPin_AlphaNumericInputShouldReturnException() {
        String userInput = "abc";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        assertThrows(NumberFormatException.class, ()->{Atm.askForPin();});
    }

    @Test
    void askForPin_MinusOneInputShouldReturnException() {
        String userInput = "-1";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        assertThrows(IllegalArgumentException.class, ()->{Atm.askForPin();});
    }
}