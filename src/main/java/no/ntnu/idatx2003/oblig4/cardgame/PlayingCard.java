package no.ntnu.idatx2003.oblig4.cardgame;

/**
 * Represents a playing card. A playing card has a number (face) between
 * 1 and 13, where 1 is called an Ace, 11 = Knight, 12 = Queen and 13 = King.
 * The card can also be one of 4 suits: Spade, Heart, Diamonds and Clubs.
 *
 * @author Sondre Odberg
 * @version 0.0.1
 */
public class PlayingCard {

  private final char suit;
  private final int face;

  /**
   * Creates an instance of a PlayingCard.
   *
   * @param suit The suit of the card ('S', 'H', 'D', 'C').
   * @param face The face value (1-13).
   * @throws IllegalArgumentException If suit or face is invalid.
   */
  public PlayingCard(char suit, int face) {
    if (suit != 'H' && suit != 'D' && suit != 'C' && suit != 'S') {
      throw new IllegalArgumentException("Parameter suit must be one of H, D, C or S");
    }

    if (face < 1 || face > 13) {
      throw new IllegalArgumentException("Parameter face must be a number between 1 to 13");
    }

    this.suit = suit;
    this.face = face;
  }

  /**
   * Returns the formatted string representation of the card.
   * Example: "H10" for 10 of Hearts.
   *
   * @return The card as a string.
   */
  public String getAsString() {
    return String.format("%s%s", suit, face);
  }

  /**
   * Returns the suit of the card.
   *
   * @return The suit character ('S', 'H', 'D', 'C').
   */
  public char getSuit() {
    return suit;
  }

  /**
   * Returns the face value of the card.
   *
   * @return The face value (1-13).
   */
  public int getFace() {
    return face;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlayingCard otherCard = (PlayingCard) o;
    return getSuit() == otherCard.getSuit() && getFace() == otherCard.getFace();
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 31 * hash + getSuit();
    hash = 31 * hash + getFace();
    return hash;
  }

  /**
   * Returns the image path for this card.
   *
   * @return The image path as a string.
   */
  public String getImagePath() {
    return "/cards/" + suit + face + ".png";
  }

}
