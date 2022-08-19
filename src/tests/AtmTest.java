package tests;

import code.Atm;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class AtmTest {

    @Test
    void main() {
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
    @Disabled
    void askForPin_MinusOneInputShouldReturnException() {
        String userInput = "-1";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        assertThrows(IllegalArgumentException.class, ()->{Atm.askForPin();});
    }
}