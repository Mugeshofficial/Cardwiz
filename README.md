# Cardwiz Game Overview
Cardwiz Game in Java This Java program implements a simple card game called Cardwiz,using Swing for the GUI

![image](https://github.com/user-attachments/assets/7aec7cc4-54dd-485b-bbcc-3ced1f37771d)

# Cardwiz Game Rules

## Objective
The goal is to have a hand value closer to 21 than the dealer without exceeding it.

## Card Values
- **Aces** can be worth 1 or 11 points (player’s choice).
- **Face cards** (Jack, Queen, King) are worth 10 points.
- All other cards are worth their numeric value.

## Game Setup
- A standard deck of 52 cards is used.
- The player and dealer are each dealt two cards.
- One of the dealer's cards is hidden.

## Gameplay

### Player's Turn
- The player can choose to "Hit" (draw another card) or "Stay" (end their turn).
- The player can continue to hit until they either stay or their total exceeds 21 (bust).

### Dealer's Turn
- Once the player stays, the dealer reveals their hidden card.
- The dealer must hit until their hand totals at least 17 points.

## Winning Conditions
- If the player’s total exceeds 21, they lose.
- If the dealer's total exceeds 21, the player wins.
- If the player’s total is higher than the dealer's total (without busting), the player wins.
- If the totals are equal, it’s a tie (push).
- If the dealer's total is higher, the dealer wins.

## Special Rules
- Aces are adjusted after the initial total calculation to

## Conclusion
Cardwiz is a simple card game with a GUI, allowing for player interaction. Future enhancements could improve user experience and graphics.
