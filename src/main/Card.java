package main;

// create a card class that has
//
public class Card {
    private final Suits suit;
    private final Value val;
    private int decidedValue;

    Card (Suits suit, Value val) {
         this.suit = suit;
         this.val = val;
    }

    // handling for aces in blackjack.
    public void decideValue (int value) {
        decidedValue = value;
    }

    public int getDecidedValue () {
        return decidedValue;
    }
    //

    public Suits getSuit () {
        return suit;
    }

    public Value getVal () {
        return val;
    }
}
