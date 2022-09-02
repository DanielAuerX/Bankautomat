package tests;

import code.Card;
import org.junit.jupiter.api.Disabled;
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
    void blockCard_tooManyTriesShouldSetIsBlockedToTrue() {
        var card = new Card(1, 1, "1", 1, false, 0);

        card.blockCard(true);

        assertTrue(card.getIsBlocked());
    }

    @Test
    void blockCard_notTooManyTriesShouldNotChangeIsBlockedFalse() {
        var card = new Card(1, 1, "1", 1, false, 0);

        card.blockCard(false);

        assertFalse(card.getIsBlocked());
    }

    @Test
    void blockCard_notTooManyTriesShouldNotChangeIsBlockedTrue() {
        var card = new Card(1, 1, "1", 1, true, 0);

        card.blockCard(false);

        assertTrue(card.getIsBlocked());
    }
}