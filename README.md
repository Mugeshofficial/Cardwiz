Cardwiz Game in Java
This Java program implements a simple card game called Cardwiz,using Swing for the GUI.

Class Overview
1. Cardwiz Class
Inner Class: Card
Represents individual playing cards with attributes for:
Type (Suit): The suit of the card (Clubs, Spades, Hearts, Diamonds).
Value (Rank): The rank of the card (Ace, 2-10, Jack, Queen, King).
Methods:
Value Retrieval: Returns the numerical value of the card.
Image Path Generation: Provides the file path for the card's image.
Game Variables
deck: Holds the deck of cards.
hiddenCard: The dealer's hidden card.
dealerHand: Stores the dealer's hand of cards.
playerHand: Stores the player's hand of cards.
dealerSum: Tracks the total points for the dealer.
playerSum: Tracks the total points for the player.
2. Game Logic
resetGame(): Resets the game state and shuffles the deck.
startGame(): Initializes the game and deals the initial cards.
buildDeck(): Creates a full deck of 52 cards.
shuffleDeck(): Randomizes the order of cards in the deck.
reduceDealerAce(): Adjusts the dealer's total if Aces cause the sum to exceed 21.
reducePlayerAce(): Adjusts the player's total similarly for Aces.
3. Game GUI
Custom JPanel (gamePanel): Handles the rendering of cards and displays the game status.
Buttons:
Hit: Draws a card for the player.
Stay: Ends the player's turn and reveals the dealer's hand.
Restart: Resets the game for a new round.
4. Main Class
The App class contains the main method, which creates an instance of Cardwiz to start the game.
Conclusion
Cardwiz is a basic card game implementation that allows player interaction through a graphical user interface. Future enhancements could include improved user experience and graphical elements.
