package tests;

import code.Card;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void checkPin_correctPinShouldReturnTrue() {
        var card = new Card(1, 1, "1", 123, false, 0);

        boolean isCorrectPin = card.checkPin(123);

        assertTrue(isCorrectPin);
    }

    @Test
    void checkPin_falsePinShouldReturnFalse() {
        var card = new Card(1, 1, "1", 123, false, 0);

        boolean isCorrectPin = card.checkPin(999);

        assertFalse(isCorrectPin);
    }

    @Test
    void checkPin_ThreePinTriesShouldReturnFalse() {
        var card = new Card(1, 1, "1", 123, false, 3);

        boolean isCorrectPin = card.checkPin(123);

        assertFalse(isCorrectPin);
    }

    @Test
    void checkPin_ThreePreviousPinTriesShouldBlockCard() {
        var card = new Card(1, 1, "1", 123, false, 3);

        boolean isBlocked = card.isBlocked();

        assertTrue(isBlocked);
    }

    @Test
    void checkPin_ThreePinTriesShouldBlockCard() {
        var card = new Card(1, 1, "1", 123, false, 0);

        card.checkPin(1);
        card.checkPin(1);
        card.checkPin(1);

        assertTrue(card.isBlocked());
    }


}