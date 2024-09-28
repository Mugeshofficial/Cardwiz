# Cardwiz Game Overview

## Class Structure

### Cardwiz Class
The main class representing the Cardwiz game.

#### Inner Class: Card
Represents individual playing cards.

- **Attributes**:
  - **Type (Suit)**: Clubs, Spades, Hearts, Diamonds.
  - **Value (Rank)**: Ace, 2-10, Jack, Queen, King.

- **Methods**:
  - **Value Retrieval**: Returns the numerical value of the card.
  - **Image Path Generation**: Provides the file path for the card's image.

### Game Variables
- **deck**: Holds the deck of cards.
- **hiddenCard**: The dealer's hidden card.
- **dealerHand**: Stores the dealer's hand of cards.
- **playerHand**: Stores the player's hand of cards.
- **dealerSum**: Tracks the total points for the dealer.
- **playerSum**: Tracks the total points for the player.

### Game Logic
- **resetGame()**: Resets the game state and shuffles the deck.
- **startGame()**: Initializes the game and deals the initial cards.
- **buildDeck()**: Creates a full deck of 52 cards.
- **shuffleDeck()**: Randomizes the order of cards in the deck.
- **reduceDealerAce()**: Adjusts dealer's total if Aces exceed 21.
- **reducePlayerAce()**: Adjusts player's total similarly for Aces.

### Game GUI
- **Custom JPanel (gamePanel)**: Renders cards and displays game status.

#### Buttons:
- **Hit**: Draws a card for the player.
- **Stay**: Ends the player's turn and reveals the dealer's hand.
- **Restart**: Resets the game for a new round.

## Main Class
### App Class
Contains the main method to start the game by creating an instance of `Cardwiz`.

## Conclusion
Cardwiz is a simple card game with a GUI, allowing for player interaction. Future enhancements could improve user experience and graphics.
