package main;

public class Deck {
    Card[] deck = new Card[52];

    // Constructor for the project.main.Deck class populates the deck with Cards.
    Deck () {
        for (int a = 0; a < Suits.values().length; a++) {
            for (int b = 0; b < Value.values().length; b++) {
                deck[((a*Value.values().length)+b)] = new Card(Suits.values()[a], Value.values()[b]);
            }
        }
    }

    // prints out all the cards in the deck.
    public void showCardsInDeck () {
        int a = 1;
        for (Card card : deck) {
            System.out.println(a+ ")  " + card.getSuit() + "  " + card.getVal());
            a++;
        }
    }

    // a helper method to check if their deck has any duplicates at any point in time.
    public void checkCardsForDuplicates () {
        int numOfDupes = 0;
        for (Card card : deck) {
            int a = 0;
            for (Card check : deck) {
                if (card.equals(check)) {
                    a++;
                }
            }
            if (a > 1) {
                numOfDupes++;
                System.out.println("duplicate found: " + card.getSuit() + "  " + card.getVal());
            }
        }
        System.out.println(numOfDupes + " duplicates found.");
    }

    // if the student opts to use an Array instead of ArrayList, their shuffle method
    // could look something like this.
    public void shuffle () {
        Card[] newDeck = new Card[52];

        for (Card card : deck) {
            int a;
            while (true) {
                a = (int) (Math.random()*deck.length);
                if (newDeck[a] != null) {
                    continue;
                }
                newDeck[a] = card;
                break;
            }
        }

        deck = newDeck;
    }

    // method a student might use if they were to use an Array for the deck rather than an arraylist
    public int getDeckSize () {
        int a = 0;
        for (Card card : deck) {
            if (card != null) {
                a++;
            }
        }
        return a;
    }

    public Card dealCard () {
        for (int a = 0; a < deck.length; a++) {
            if (deck[a] != null) {
                Card c = deck[a];
                deck[a] = null;
                return c;
            }
        }
        return null;
    }
}
