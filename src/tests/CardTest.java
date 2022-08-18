package tests;

import code.Card;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void checkPin_correctPinShouldReturnTrue() {
        var card = new Card(1, 1, 1, 123, false);

        boolean isCorrectPin = card.checkPin(123);

        assertTrue(isCorrectPin);
    }

    @Test
    void checkPin_falsePinShouldReturnFalse() {
        var card = new Card(1, 1, 1, 123, false);

        boolean isCorrectPin = card.checkPin(999);

        assertFalse(isCorrectPin);
    }

    @Test
    @Disabled("isBlocked cant be accessed")
    void blockCard() {
        var card = new Card(1, 1, 1, 1, false);

        card.blockCard(true);

        //assertTrue();

    }
}