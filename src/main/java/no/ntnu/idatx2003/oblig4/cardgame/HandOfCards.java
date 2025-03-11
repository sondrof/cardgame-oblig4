package no.ntnu.idatx2003.oblig4.cardgame;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents a hand of playing cards.
 * A hand consists of multiple cards that can be analyzed
 * for different combinations such as flushes or special cards.
 *
 * @author Sondre Odberg
 * @version 0.0.1
 */
public class HandOfCards {
  private final List<PlayingCard> hand;

  /**
   * Constructs an empty hand.
   */
  public HandOfCards() {
    this.hand = new ArrayList<>();
  }

  public List<PlayingCard> getCards() {
    return new ArrayList<>(hand);
  }

  /**
   * Adds a playing card to the hand.
   *
   * @param card The card to add.
   * @throws IllegalArgumentException if the card is null.
   */
  public void addCard(PlayingCard card) {
    if (card == null) {
      throw new IllegalArgumentException("Card cannot be null");
    }
    hand.add(card);
  }

  /**
   * Calculates the sum of face values in the hand.
   *
   * @return The total sum of card values.
   */
  public int sumOfFaces() {
    return hand.stream()
        .mapToInt(PlayingCard::getFace)
        .sum();
  }

  /**
   * Returns a formatted string of all Heart cards in the hand.
   *
   * @return A string containing only Heart cards, or "No Hearts".
   */
  public String getHeartsAsString() {
    String hearts = hand.stream()
        .filter(card -> card.getSuit() == 'H')
        .map(PlayingCard::getAsString)
        .collect(Collectors.joining(" "));
    return hearts.isEmpty() ? "No Hearts" : hearts;
  }

  /**
   * Checks if the hand contains the Queen of Spades.
   *
   * @return True if the Queen of Spades is in hand, false otherwise.
   */
  public boolean hasQueenOfSpades() {
    return hand.stream()
        .anyMatch(card -> card.getSuit() == 'S' && card.getFace() == 12);
  }

  /**
   * Checks if the hand contains a flush (5 cards of the same suit).
   *
   * @return True if the hand is a flush, false otherwise.
   */
  public boolean isFlush() {
    return hand.stream()
        .collect(Collectors.groupingBy(PlayingCard::getSuit, Collectors.counting()))
        .values().stream()
        .anyMatch(count -> count >= 5);
  }

  @Override
  public String toString() {
    if (hand.isEmpty()) {
      return "Empty Hand";
    }
    return hand.stream()
        .map(PlayingCard::getAsString)
        .collect(Collectors.joining(", "));
  }
}
