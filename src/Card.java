public class Card {
    private final Suits suit;
    private final Value val;

    Card (Suits suit, Value val) {
         this.suit = suit;
         this.val = val;
    }

    public Suits getSuit () {
        return suit;
    }

    public Value getVal () {
        return val;
    }
}
