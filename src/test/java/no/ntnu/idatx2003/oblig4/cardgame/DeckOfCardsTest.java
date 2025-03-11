package no.ntnu.idatx2003.oblig4.cardgame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class DeckOfCardsTest {

  @Test
  void deckHas52CardsOnInit() {
    DeckOfCards deck = new DeckOfCards();
    assertEquals(52, deck.getRemainingCards());
  }

  @Test
  void drawHandRemovesCardsFromDeck() {
    DeckOfCards deck = new DeckOfCards();
    List<PlayingCard> hand = deck.dealHand(5);

    assertEquals(5, hand.size());
    assertEquals(47, deck.getRemainingCards());
  }

  @Test
  void cannotDrawMoreCardsThanAvailable() {
    DeckOfCards deck = new DeckOfCards();
    deck.dealHand(52);

    assertThrows(IllegalStateException.class, () -> deck.dealHand(1));
  }
}
