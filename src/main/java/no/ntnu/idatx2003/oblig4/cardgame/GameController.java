package no.ntnu.idatx2003.oblig4.cardgame;

import java.util.List;

/**
 * Controls the logic of the card game.
 * Manages dealing hands and tracking the deck state.
 *
 * @author Sondre Odberg
 * @version 0.0.1
 */
public class GameController {
  private final DeckOfCards deck;
  private HandOfCards currentHand;

  /**
   * Initializes a new deck.
   */
  public GameController() {
    deck = new DeckOfCards();
    deck.shuffle();
  }

  /**
   * Deals a new hand of cards.
   *
   * @param n Number of cards to deal.
   * @return The dealt hand.
   */
  public HandOfCards dealHand(int n) {
    if (deck.getRemainingCards() < n) {
      System.out.println("Not enough cards left in deck! Current size: " + deck.getRemainingCards());
      throw new IllegalStateException("Not enough cards left in the deck!");
    }
    currentHand = new HandOfCards();
    List<PlayingCard> drawnCards = deck.dealHand(n);
    drawnCards.forEach(currentHand::addCard);

    System.out.println("New hand dealt: " + currentHand);
    return currentHand;
  }



  /**
   * Checks if the current hand is a flush.
   *
   * @return true if it's a flush, false otherwise.
   */
  public boolean isFlush() {
    return currentHand != null && currentHand.isFlush();
  }

  /**
   * Retrieves the current hand of the player.
   *
   * @return The current hand, or null if no hand has been dealt.
   */
  public HandOfCards getCurrentHand() {
    return currentHand;
  }

  /**
   * Gets the number of remaining cards.
   *
   * @return Number of remaining cards in the deck.
   */
  public int getRemainingCards() {
    return deck.getRemainingCards();
  }


}
