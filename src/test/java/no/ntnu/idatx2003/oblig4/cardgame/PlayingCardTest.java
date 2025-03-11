package no.ntnu.idatx2003.oblig4.cardgame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayingCardTest {

@Test
  void validCardCreation() {
    PlayingCard card = new PlayingCard('H', 10);
    assertEquals('H', card.getSuit());
    assertEquals(10, card.getFace());
    assertEquals("H10", card.getAsString());
  }

  @Test
  void invalidSuitThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> new PlayingCard('X', 5));
  }

  @Test
  void invalidFaceThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> new PlayingCard('H', 14));
    assertThrows(IllegalArgumentException.class, () -> new PlayingCard('H', 0));
  }

  @Test
  void testCardEquality() {
    PlayingCard card1 = new PlayingCard('S', 12);
    PlayingCard card2 = new PlayingCard('S', 12);
    PlayingCard card3 = new PlayingCard('H', 12);

    assertEquals(card1, card2);
    assertNotEquals(card1, card3);
  }
}
