package no.ntnu.idatx2003.oblig4.cardgame;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

/**
 * Represents the graphical user interface (GUI) for the card game.
 * The view interacts with the GameController to manage game logic.
 *
 * @author Sondre Odberg
 * @version 0.0.1
 */
public class GameView extends Application {
  private GameController controller;
  private HBox handDisplay;
  private Text flushText, sumLabel, heartsLabel, queenLabel, deckCountLabel;
  private Button resetDeckButton;

  /**
   * Initializes and starts the JavaFX application.
   *
   * @param primaryStage The main stage of the application.
   */
  @Override
  public void start(Stage primaryStage) {
    controller = new GameController();

    BorderPane borderPane = new BorderPane();
    borderPane.setTop(createMenuBar());
    borderPane.setCenter(createMainContent());

    Scene scene = new Scene(borderPane, 600, 400);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Card Game");
    primaryStage.show();
  }

  /**
   * Creates the menu bar.
   *
   * @return A JavaFX MenuBar.
   */
  private MenuBar createMenuBar() {
    MenuItem closeMenuItem = new MenuItem("Close");
    closeMenuItem.setOnAction(event -> System.exit(0));

    Menu fileMenu = new Menu("File");
    fileMenu.getItems().addAll(closeMenuItem);

    MenuBar menuBar = new MenuBar();
    menuBar.getMenus().addAll(fileMenu);
    return menuBar;
  }

  /**
   * Creates the main game layout.
   *
   * @return A Pane containing the game UI.
   */
  private Pane createMainContent() {
    BorderPane mainLayout = new BorderPane();

    handDisplay = new HBox(10);
    handDisplay.setMinHeight(120);
    handDisplay.setStyle("-fx-border-color: black; -fx-padding: 20px;");
    handDisplay.setAlignment(Pos.CENTER);
    mainLayout.setTop(handDisplay);

    VBox statsPane = createStatsPane();
    mainLayout.setCenter(statsPane);

    VBox buttonsPane = createButtonsPane();
    mainLayout.setRight(buttonsPane);

    return mainLayout;
  }

  /**
   * Creates the game statistics panel.
   *
   * @return A VBox containing game statistics.
   */
  private VBox createStatsPane() {
    sumLabel = new Text("Sum: N/A");
    heartsLabel = new Text("Hearts: N/A");
    queenLabel = new Text("Queen of Spades: N/A");
    flushText = new Text("Flush: N/A");
    deckCountLabel = new Text("Cards Left: " + controller.getRemainingCards());

    VBox statsPane = new VBox(10, sumLabel, heartsLabel, queenLabel, flushText, deckCountLabel);
    statsPane.setAlignment(Pos.CENTER_LEFT);
    statsPane.setStyle("-fx-padding: 20px;");
    return statsPane;
  }

  /**
   * Creates the control buttons panel.
   *
   * @return A VBox containing game controls.
   */
  private VBox createButtonsPane() {
    Button dealButton = new Button("Deal Hand");
    dealButton.setOnAction(e -> dealHand());

    Button checkButton = new Button("Check Hand");
    checkButton.setOnAction(e -> checkHand());

    resetDeckButton = new Button("Reset Deck");
    resetDeckButton.setOnAction(e -> resetDeck());

    VBox buttonsPane = new VBox(10, dealButton, checkButton, resetDeckButton);
    buttonsPane.setAlignment(Pos.CENTER_RIGHT);
    buttonsPane.setStyle("-fx-padding: 20px;");
    return buttonsPane;
  }

  /**
   * Deals a new hand and updates the UI.
   */
  private void dealHand() {
    HandOfCards hand = controller.dealHand(5);
    updateHandDisplay(hand);
    updateDeckCount();
  }

  /**
   * Checks the current hand and updates the UI.
   */
  private void checkHand() {
    HandOfCards hand = controller.getCurrentHand();

    if (hand == null) {
      return;
    }

    sumLabel.setText("Sum: " + hand.sumOfFaces());
    heartsLabel.setText("Hearts: " + hand.getHeartsAsString());
    queenLabel.setText("Queen of Spades: " + (hand.hasQueenOfSpades() ? "Yes" : "No"));
    flushText.setText("Flush: " + (hand.isFlush() ? "Yes" : "No"));
  }

  /**
   * Updates the hand display with images.
   *
   * @param hand The hand to display.
   */
  private void updateHandDisplay(HandOfCards hand) {
    handDisplay.getChildren().clear();

    List<PlayingCard> cards = hand.getCards();
    for (PlayingCard card : cards) {
      String imagePath = card.getImagePath();
      try {
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(98);
        imageView.setFitWidth(73);
        imageView.setPreserveRatio(true);
        handDisplay.getChildren().add(imageView);
      } catch (Exception e) {
        System.out.println("Could not load image: " + imagePath);
      }
    }
  }

  /**
   * Resets the deck and clears the current hand.
   */
  private void resetDeck() {
    controller = new GameController();  // Create a new game instance
    handDisplay.getChildren().clear();  // Clear card images
    deckCountLabel.setText("Cards Left: " + controller.getRemainingCards());
  }

  /**
   * Updates the deck count display.
   */
  private void updateDeckCount() {
    deckCountLabel.setText("Cards Left: " + controller.getRemainingCards());
  }

  /**
   * Launches the application.
   *
   * @param args Command-line arguments.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
