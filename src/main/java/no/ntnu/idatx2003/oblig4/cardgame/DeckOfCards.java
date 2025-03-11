package no.ntnu.idatx2003.oblig4.cardgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Represents a standard deck of 52 playing cards.
 * The deck supports shuffling, dealing, and checking remaining cards.
 *
 * @author Sondre Odberg
 * @version 0.0.1
 */
public class DeckOfCards {
  private final List<PlayingCard> deck;
  private Random random;

  /**
   * Constructs a full deck of 52 playing cards and shuffles it.
   */
  public DeckOfCards() {
    deck = new ArrayList<>();
    char[] suits = { 'S', 'H', 'D', 'C' };

    for (char suit : suits) {
      for (int face = 1; face <= 13; face++) {
        deck.add(new PlayingCard(suit, face));
      }
    }
    shuffle();
  }

  /**
   * Returns the deck of cards.
   *
   * @return List of PlayingCard objects.
   */
  public List<PlayingCard> getDeck() {
    return deck;
  }

  /**
   * Shuffles the deck.
   */
  public void shuffle() {
    Collections.shuffle(deck);
  }

  /**
   * Draws a card from the deck.
   *
   * @return A PlayingCard object, or null if the deck is empty.
   */
  public PlayingCard drawCard() {
    if (!deck.isEmpty()) {
      return deck.remove(deck.size() - 1);
    }
    return null;
  }

  /**
   * Deals a hand of n cards from the deck.
   *
   * @param n The number of cards to deal.
   * @return A list of playing cards.
   * @throws IllegalStateException If not enough cards are available.
   */
  public List<PlayingCard> dealHand(int n) {
    if (n < 1 || n > deck.size()) {
      throw new IllegalArgumentException("Invalid number of cards requested.");
    }

    List<PlayingCard> hand = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      hand.add(deck.remove(deck.size() - 1));
    }
    return hand;
  }

  /**
   * Gets the number of remaining cards in the deck.
   *
   * @return Number of remaining cards.
   */
  public int getRemainingCards() {
    return deck.size();
  }
}
