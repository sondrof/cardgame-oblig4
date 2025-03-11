package no.ntnu.idatx2003.oblig4.cardgame;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class HandOfCardsTest {

  @Test
  void addCardToHand() {
    HandOfCards hand = new HandOfCards();
    PlayingCard card = new PlayingCard('D', 8);
    hand.addCard(card);

    assertEquals(1, hand.getCards().size());
    assertEquals(card, hand.getCards().get(0));
  }

  @Test
  void sumOfFaces() {
    HandOfCards hand = new HandOfCards();
    hand.addCard(new PlayingCard('H', 5));
    hand.addCard(new PlayingCard('D', 7));
    hand.addCard(new PlayingCard('C', 9));

    assertEquals(21, hand.sumOfFaces());
  }

  @Test
  void getHeartsAsString() {
    HandOfCards hand = new HandOfCards();
    hand.addCard(new PlayingCard('H', 2));
    hand.addCard(new PlayingCard('H', 10));
    hand.addCard(new PlayingCard('D', 5));

    assertEquals("H2 H10", hand.getHeartsAsString());
  }

  @Test
  void checkForFlush() {
    HandOfCards hand = new HandOfCards();
    hand.addCard(new PlayingCard('S', 3));
    hand.addCard(new PlayingCard('S', 6));
    hand.addCard(new PlayingCard('S', 9));
    hand.addCard(new PlayingCard('S', 12));
    hand.addCard(new PlayingCard('S', 2));

    assertTrue(hand.isFlush());
  }

  @Test
  void queenOfSpadesExists() {
    HandOfCards hand = new HandOfCards();
    hand.addCard(new PlayingCard('H', 5));
    hand.addCard(new PlayingCard('S', 12)); // Queen of Spades

    assertTrue(hand.hasQueenOfSpades());
  }
}
