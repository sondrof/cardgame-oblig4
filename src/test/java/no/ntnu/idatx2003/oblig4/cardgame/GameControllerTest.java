package no.ntnu.idatx2003.oblig4.cardgame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

  @Test
  void dealHandReturnsHand() {
    GameController controller = new GameController();
    HandOfCards hand = controller.dealHand(5);

    assertNotNull(hand);
    assertEquals(5, hand.getCards().size());
  }

  @Test
  void getRemainingCardsReflectsDeckUsage() {
    GameController controller = new GameController();
    controller.dealHand(5);

    assertEquals(47, controller.getRemainingCards());
  }

}
